package pickmeal.dream.pj.posting.repository;

import java.util.List;

import pickmeal.dream.pj.posting.domain.Comment;

public class CommentDaoImpl implements CommentDao {

	@Override
	public void addComment(Comment comment) {
		String sql = "INSERT INTO Member(memberType, email, passwd, nickName, birth, gender, profileImgPath)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";

	}

	@Override
	public Comment findLastAddComment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment updateComment(Comment comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteComment(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Comment> findAllCommentByMemberId(long memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> findAllCommentByPostId(long postId) {
		// TODO Auto-generated method stub
		return null;
	}

}
