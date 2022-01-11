package pickmeal.dream.pj.posting.service;

import java.util.List;

import pickmeal.dream.pj.posting.domain.Comment;

public interface CommentService {
	/**
	 * 댓글 추가
	 * @param comment
	 * @return
	 */
	public Comment addComment(Comment comment);
	
	/**
	 * 댓글 수정
	 * @param comment
	 * @return
	 */
	public Comment updateComment(Comment comment);
	
	/**
	 * 댓글 삭제
	 * @param id
	 */
	public boolean deleteComment(Comment comment);
	
	/**
	 * 업데이트 할 댓글 불러오기 (전의 내용과 다른 지 확인해야한다)
	 * @param comment
	 * @return
	 */
	public Comment findCommentById(Comment comment);
	
	/**
	 * 해당 사용자의 모든 댓글 들고오기
	 * @param memberId
	 * @return
	 */
	public List<Comment> findAllCommentByMemberId(long memberId, char category);
	
	/**
	 * 해당 게시글의 모든 댓글 들고오기
	 * @param postId
	 * @return
	 */
	public List<Comment> findAllCommentByPostId(long postId, char category);
}
