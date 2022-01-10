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
	public void deleteComment(long id);
	
	/**
	 * 해당 사용자의 모든 댓글 들고오기
	 * @param memberId
	 * @return
	 */
	public List<Comment> findAllCommentByMemberId(long memberId);
	
	/**
	 * 해당 게시글의 모든 댓글 들고오기
	 * @param postId
	 * @return
	 */
	public List<Comment> findAllCommentByPostId(long postId);
}
