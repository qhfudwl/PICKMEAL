package pickmeal.dream.pj.chat.service;

import java.util.List;

import pickmeal.dream.pj.chat.domain.Chat;
import pickmeal.dream.pj.member.domain.Member;

public interface ChatService {
	
	/**
	 * 채팅 추가
	 * writerId와 commenterId 만 있으면 된다.
	 * 해당 파일은 각 nickName 을 이용해서 파일 생성
	 * @param chat
	 */
	public void addChat(Chat chat);
	
	/**
	 * 해당 채팅 정보 업데이트
	 * @param chat
	 */
	public void updateChat(Chat chat);
	
	/**
	 * 해당 채팅을 지운다.
	 * 일정 기간 후 자동 삭제
	 * 리스너에서 최초 한번 돌려주자
	 * @param chat
	 */
	public void deleteChat();
	
	/**
	 * 게시글 작성자 id 와 댓글 작성자 id 로 채팅을 가져온다.
	 * @param writerId
	 * @param commenterId
	 * @return
	 */
	public Chat findChatByWriterIdAndCommenterId(Chat chat);
	
	/**
	 * 해당 사용자의 모든 채팅 목록을 가져온다.
	 * @param memberId
	 * @return
	 */
	public List<Chat> findAllChatsByMemberId(long memberId);
	
	/**
	 * ajax의 경우 일정 갯수만큼 가지고 온다.
	 * 가져왔던 채팅 목록 id의 뒤에것부터 가져온다.
	 * @param memberId
	 * @return
	 */
	public List<Chat> findLimitedChatsByMemberId(long memberId, long startId, int limit);
	
	/**
	 * writer 및 commenter 셋팅해오기
	 * @param memberId
	 * @return
	 */
	public Member makeChatter(long memberId);
	
	/**
	 * 해당 채팅의 내용을 외부 파일로 저장한다.
	 * 형식은 wrtier.nickName_commenter.nickName(member.nickName).txt
	 * @param tagData
	 * @param chat
	 */
	public void uploadChatContent(List<String> tagData, Chat chat);

	/**
	 * 해당 채팅 내용을 외부파일로부터 가져온다.
	 * 형식은 wrtier.nickName_commenter.nickName(member.nickName).txt
	 * @param chat
	 * @return
	 */
	public List<String> downloadChatContent(Chat chat);
}
