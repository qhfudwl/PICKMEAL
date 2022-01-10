package pickmeal.dream.pj.posting.service;

import java.util.List;

import org.springframework.stereotype.Service;

import pickmeal.dream.pj.posting.domain.Comment;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

	@Override
	public Comment addComment(Comment comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment updateComment(Comment comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteComment(Comment comment) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Comment> findAllCommentByMemberId(long memberId, char category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> findAllCommentByPostId(long postId, char category) {
		// TODO Auto-generated method stub
		return null;
	}

}
