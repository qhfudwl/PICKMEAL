package pickmeal.dream.pj.posting.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pickmeal.dream.pj.posting.domain.Comment;

@Repository("commentDao")
public class CommentDaoImpl implements CommentDao {
	
	@Autowired
	private JdbcTemplate jt;

	@Override
	public void addComment(Comment comment) {
		String tableName = decideTableName(comment.getPosting().getCategory());
		
		String sql = "INSERT INTO " + tableName + "(memberId, postId, content)"
				+ " VALUES (?, ?, ?)";
		
		jt.update(sql, comment.getMember().getId(), comment.getPosting().getId(), comment.getContent());
	}

	@Override
	public Comment findLastAddComment(long memberId, char category) {
		String tableName = decideTableName(category);
		String sql = "SELECT id, memberId, postId, content, regDate"
				+ " FROM " + tableName + " WHERE id=LAST_INSERT_ID() AND memberId=?";
		return jt.queryForObject(sql, new CommentRowMapper(), memberId);
	}

	@Override
	public void updateComment(Comment comment) {
		String tableName = decideTableName(comment.getPosting().getCategory());
		String sql = "UPDATE " + tableName + " SET content=? WHERE id=?";
		
		jt.update(sql, comment.getContent(), comment.getId());
	}

	@Override
	public void deleteComment(Comment comment) {
		String tableName = decideTableName(comment.getPosting().getCategory());
		String sql = "DELETE FROM " + tableName + " WHERE id=?";

		jt.update(sql, comment.getId());
	}

	@Override
	public Comment findCommentById(Comment comment) {
		String tableName = decideTableName(comment.getPosting().getCategory());
		String sql = "SELECT id, memberId, postId, content, regDate"
				+ " FROM " + tableName + " WHERE id=?";
		
		return jt.queryForObject(sql, new CommentRowMapper(), comment.getId());
	}

	@Override
	public boolean isCommentById(Comment comment) {
		String tableName = decideTableName(comment.getPosting().getCategory());
		String sql = "SELECT EXISTS (SELECT id from " + tableName + " WHERE id=?)";
		return jt.queryForObject(sql, Boolean.class, comment.getId());
	}

	@Override
	public List<Comment> findAllCommentByMemberId(long memberId, char category) {
		String tableName = decideTableName(category);
		String sql = "SELECT id, memberId, postId, content, regDate"
				+ " FROM " + tableName + " WHERE memberId=?";
		
		return jt.query(sql, new CommentRowMapper(), memberId);
	}

	@Override
	public List<Comment> findCommentsByPostId(long postId, char category, int start, int end) {
		String tableName = decideTableName(category);
		// LIMIT 은 INDEX가 0 에서부터 시작
		// 5, 10 이면 우리로 치면 6번째 행부터 10개 이렇게다
		String sql = "SELECT id, memberId, postId, content, regDate"
				+ " FROM " + tableName + " WHERE postId=? LIMIT ?, ?";
		
		return jt.query(sql, new CommentRowMapper(), postId, start, end);
	}

	@Override
	public int countCommentByPostId(long postId, char category) {
		String tableName = decideTableName(category);
		String sql = "SELECT COUNT(*) FROM " + tableName + " GROUP BY postId=?";
		return jt.queryForObject(sql, Integer.class, postId);
	}
	
	/**
	 * 테이블을 결정하기 위한 dao 만의 메소드
	 * @param category
	 * @return
	 */
	private String decideTableName(char category) {
		String tableName = null;
		// 테이블 결정
		if (category == 'R') {
			tableName = "RecommendRestaurantComment";
		} else if (category == 'E') {
			tableName = "TogetherEatingComment";
		}
		return tableName;
	}

}
