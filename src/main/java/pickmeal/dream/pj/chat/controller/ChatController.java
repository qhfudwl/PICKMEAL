package pickmeal.dream.pj.chat.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import pickmeal.dream.pj.chat.command.ChatCommand;
import pickmeal.dream.pj.chat.domain.Chat;
import pickmeal.dream.pj.chat.service.ChatService;
import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.member.service.MemberService;
import pickmeal.dream.pj.web.util.PresentTime;

@Controller
@Slf4j
public class ChatController {
	
	@Autowired
	MemberService ms;
	
	@Autowired
	ChatService cs;
	
	@Autowired
	PresentTime pt;
	
	/**
	 * 댓글을 눌러서 채팅 화면으로 가는 경우
	 * @param writer
	 * @param commenter
	 * @param session
	 * @return
	 */
	@GetMapping("/chat/chatListByComment")
	public ModelAndView chatList(@RequestParam("writer") String writer, @RequestParam("commenter") String commenter,
			HttpSession session) {
		ModelAndView mav = new ModelAndView();
		// 좌측에 해당 사용자의 채팅 목록을 불러온다.
		Member member = (Member)session.getAttribute("member");
		List<Chat> chats = cs.findAllChatsByMemberId(member.getId());
		mav.addObject("chats", chats);
		// 우측에 이번에 새롭게 시작할 채팅 화면을 띄워야한다.
		// writer와 commenter 모두를 session 에 넣어줘야한다.
		Member enterWriter = cs.makeChatter(Long.parseLong(writer));
		Member enterCommenter = cs.makeChatter(Long.parseLong(commenter));
		// handler에서 사용
		session.setAttribute("writer", enterWriter);
		session.setAttribute("commenter", enterCommenter);
		
		// 채팅이 시작한다는 표시
		mav.addObject("chatStart", "true");
		
		Chat chat = new Chat();
		chat.setWriter(enterWriter);
		chat.setCommenter(enterCommenter);
		chat.setMember(member);
		mav.addObject("chat", cs.findChatByWriterIdAndCommenterId(chat));
		
		mav.setViewName("chat/chat_list");
		return mav;
	}
	
	
	/**
	 * 헤더의 아이콘을 눌러서 들어온다.
	 * @param member
	 * @return
	 */
	@GetMapping("/chat/chatListByIcon")
	public ModelAndView goChatListByIcon(@SessionAttribute("member") Member member) {
		ModelAndView mav = new ModelAndView();
		
		List<Chat> chats = cs.findAllChatsByMemberId(member.getId());
		mav.addObject("chats", chats);

		// 아직 채팅이 시작되면 안된다.
		mav.addObject("chatStart", "false");
		mav.setViewName("chat/chat_list");
		return mav;
	}
	
	@GetMapping("/chat/chatAlarm")
	public ResponseEntity<?> chatAlarm(HttpSession session) {
		session.setAttribute("chatCount", "false");
		 // 10800000 : 3시간
		
		try {
			TimeUnit.HOURS.sleep(3);
			session.setAttribute("chatCount", "true");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return ResponseEntity.ok("time done");
	}
	
	/**
	 * 파일 업로드 시에 채팅 추가를 같이 해준다.
	 * @param chatCommand
	 */
	@PostMapping("/chat/uploadFile")
	@ResponseBody
	public void uploadChattingFile(@ModelAttribute ChatCommand chatCommand
			, @SessionAttribute("member") Member member) {
		List<String> fileText = chatCommand.getFileText();
		Chat chat = new Chat();
		chat.setWriter(ms.findMemberById(chatCommand.getWriterId()));
		chat.setCommenter(ms.findMemberById(chatCommand.getCommenterId()));
		chat.setMember(member);
		cs.uploadChatContent(fileText, chat);
		cs.addChat(chat);
	}
	

	/**
	 * 파일 다운로드 시 채팅방에 들어갔다는 의미이기 대문에 wrtier랑 commenter 를 session 에 넣는다.
	 * @param chatCommand
	 * @param session
	 * @return
	 */
	@PostMapping("/chat/downloadFile")
	public ResponseEntity<?> downloadChattingFile(@ModelAttribute ChatCommand chatCommand, 
			HttpSession session) {
		Member writer = ms.findMemberById(chatCommand.getWriterId());
		Member commenter = ms.findMemberById(chatCommand.getCommenterId());
		Chat chat = new Chat();
		chat.setWriter(writer);
		chat.setCommenter(commenter);
		chat.setMember((Member)session.getAttribute("member"));
		// 다른 채팅방에 들어가려고 하는 것이기 때문에 새롭게 writer 와 commenter 를 setting 해준다
		session.setAttribute("writer", writer);
		session.setAttribute("commenter", commenter);
		return ResponseEntity.ok(cs.downloadChatContent(chat));
	}
}
