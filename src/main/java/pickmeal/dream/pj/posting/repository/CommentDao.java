package pickmeal.dream.pj.posting.repository;

import java.util.List;

import pickmeal.dream.pj.posting.domain.Comment;

public interface CommentDao {
	/**
	 * 댓글 추가
	 * @param comment
	 * @return
	 */
	public void addComment(Comment comment);
	
	/**
	 * 마지막으로 insert 된 댓글 가져오기
	 * @return
	 */
	public Comment findLastAddComment(long memberId, char category);
	
	/**
	 * 댓글 수정
	 * @param comment
	 * @return
	 */
	public void updateComment(Comment comment);
	
	/**
	 * 댓글 삭제
	 * @param id
	 */
	public void deleteComment(Comment comment);
	
	/**
	 * 업데이트 할 댓글 불러오기 (전의 내용과 다른 지 확인해야한다)
	 * @param comment
	 * @return
	 */
	public Comment findCommentById(Comment comment);
	
	/**
	 * 해당 댓글이 있는지 확인
	 * @param comment
	 * @return
	 */
	public boolean isCommentById(Comment comment);
	
	/**
	 * 해당 사용자의 모든 댓글 들고오기
	 * @param memberId
	 * @return
	 */
	public List<Comment> findAllCommentByMemberId(long memberId, char category);
	
	/**
	 * 해당 게시글의 특정 숫자의 댓글 들고오기
	 * 프로필 사진 / 식력 포인트 / 신뢰 온도 / 닉네임이 셋팅된 멤버를
	 * 댓글 객체에 셋팅한다.
	 * 
	 * @param postId, category, start, end
	 * @return
	 */
	public List<Comment> findCommentsByPostId(long postId, char category, int start, int end);
	
	/**
	 * 해당 게시물에 총 몇개의 댓글이 있는지 반환
	 * @param postId
	 * @param category
	 * @return
	 */
	public int countCommentByPostId(long postId, char category);
}
