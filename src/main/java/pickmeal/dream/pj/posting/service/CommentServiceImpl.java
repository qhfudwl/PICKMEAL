package pickmeal.dream.pj.posting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pickmeal.dream.pj.posting.domain.Comment;
import pickmeal.dream.pj.posting.repository.CommentDao;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentDao cd;

	@Override
	public Comment addComment(Comment comment) {
		// 추가 후
		cd.addComment(comment);
		// 아이디랑 등록 날짜까지 한 것을 가져와야한다.
		return cd.findLastAddComment(comment.getMember().getId(), comment.getPosting().getCategory());
	}

	@Override
	public Comment updateComment(Comment comment) {
		Comment beforeComment = findCommentById(comment);
		// 수정할 댓글 내용이 이 전과 같으면 업데이트 불가
		if (beforeComment.getContent().equals(comment.getContent())) {
			return comment;
		}
		// 업데이트 후
		cd.updateComment(comment);
		// 아이디랑 등록 날짜까지 한 것을 가져와야한다.
		return cd.findCommentById(comment);
	}

	@Override
	public boolean deleteComment(Comment comment) {
		// 댓글을 삭제 후
		cd.deleteComment(comment);
		// 제대로 삭제되었는지 확인
		if (cd.isCommentById(comment)) { // 해당 댓글이 존재하면 거짓
			return false;
		}
		return true;
	}

	@Override
	public Comment findCommentById(Comment comment) {
		return cd.findCommentById(comment);
	}

	@Override
	public List<Comment> findAllCommentByMemberId(long memberId, char category) {
		return cd.findAllCommentByMemberId(memberId, category);
	}

	@Override
	public List<Comment> findAllCommentByPostId(long postId, char category) {
		return cd.findAllCommentByMemberId(postId, category);
	}

}
