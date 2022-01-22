package pickmeal.dream.pj.web.handler;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.member.service.MemberService;
import pickmeal.dream.pj.web.util.Validator;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {

	private Map<String, WebSocketSession> loginMemberMap = new HashMap<String, WebSocketSession>();
	
	@Autowired
	MemberService ms;
	
	@Autowired
	Validator v;
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
		log.info("#WebSocketHandler, afterConnectionEstablished");
		Map<String, Object> map = session.getAttributes(); // 현재 접속한 사용자의 session scope 을 반환
		Member member = (Member)map.get("member"); 
		
		loginMemberMap.put(member.getNickName(), session); // 채팅 시 바로 닉네임을 사용해서 뽑아내기 위함
		
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage messageline) throws Exception {
		log.info("#WebSocketHandler, handleMessage");
		
		String line = messageline.getPayload();
		Map<String, Object> map = session.getAttributes();
		
		// 현재 로그인 중인 클라이언트의 세션 내 정보
		Member commenter = (Member)map.get("commenter");
		Member writer = (Member)map.get("writer");
		
		// 화면에 처음 들어왔다면
		// 글쓴이와 댓글 작성자 구분
		if (line.contains("first send")) {
			String[] lines = line.split(":");
			WebSocketSession commenterS = loginMemberMap.get(lines[1]);
			if (v.isEmpty(commenterS)) {
				session.sendMessage(new TextMessage("댓글 작성자 비로그인 중"));
			} else {
				session.sendMessage(new TextMessage("댓글 작성자 로그인 중"));
				String msg = writer.getNickName() + "와 채팅이 시작되었습니다."
						+ "<br>채팅에 참석을 원한다면 아래의 채팅 바로가기 버튼을 눌러주세요."
						+ "<br>참석을 원하지 않을 시 창을 닫아주세요.//" + writer.getId() + "//" + writer.getNickName()
						+ "//" + commenter.getId() + "//" + commenter.getNickName();
				// commenter의 session scope 에 writer 랑 commenter 를 넣어준다.
				
				commenterS.sendMessage(new TextMessage(msg));
				
			}
			return;
		}

		String name = line.substring(0, line.indexOf(":")); // 보낸 사람 닉네임
		String message = line.substring(line.indexOf(":")+1); // 내용
		
		
		// 채팅 거절 시
		if (message.contains("님은 시간이 부족하네요ㅠㅠ")) {
			WebSocketSession writerS = loginMemberMap.get(name);
			writerS.sendMessage(new TextMessage(name + ":" + message));
			return;
		}
		// 채팅 수락 시
		if (message.contains("님이 입장했습니다.")) {
			WebSocketSession writerS = loginMemberMap.get(writer.getNickName());
			writerS.sendMessage(new TextMessage(name + ":" + message));
			return;
		}
		
		// 작성자와 댓글을 적은 사람에게만 보낸다.
		for (String email : loginMemberMap.keySet()) {
			if (email.equals(writer.getNickName()) || email.equals(commenter.getNickName())) {
				WebSocketSession s = loginMemberMap.get(email);
				s.sendMessage(new TextMessage(name + ":" + message));
			}
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		Map<String, Object> map = session.getAttributes();
		// session scope 에 저장해 둔 writer와 commenter 도 삭제
		map.remove("writer");
		map.remove("commenter");
		
		Member member = (Member)map.get("member");
		loginMemberMap.remove(member.getNickName());
		log.info("#WebSocketHandler, afterConnectionClosed");
	}
}
