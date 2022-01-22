package pickmeal.dream.pj.chat.repository;

import java.util.List;

import pickmeal.dream.pj.chat.domain.Chat;

public interface ChatDao {
	
	/**
	 * 채팅 추가
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
	 * 일정 기간이 지나면 자동 삭제
	 * @param chat
	 */
	public void deleteChat();
	
	/**
	 * 해당 사용자에게 채팅 목록이 있는가?
	 * @param memberId
	 * @return
	 */
	public boolean isChatByMemberId(long memberId);
	
	/**
	 * 해당 테이블이 있는가?
	 * 테이블이 있을 경우 파일도 존재하는 것이다.
	 * 해당 멤버에 대해 writer 와 commenter 가 일치하는 것이 있는가?
	 * memberId 도 있어야한다.
	 * @param wrtierId
	 * @param commenterId
	 * @return
	 */
	public boolean isChatByWriterIdAndCommenterId(Chat chat);
	
	/**
	 * 해당 채팅이 있는가?
	 * @param memberId
	 * @return
	 */
	public boolean isChatById(long id);
	
	/**
	 * 해당 사용자의 게시글 작성자 id 와 댓글 작성자 id 로 채팅을 가져온다.
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
}
