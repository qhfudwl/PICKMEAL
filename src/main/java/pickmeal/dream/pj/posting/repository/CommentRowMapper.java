package pickmeal.dream.pj.posting.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.posting.domain.Comment;
import pickmeal.dream.pj.posting.domain.Posting;

public class CommentRowMapper implements RowMapper<Comment> {

	@Override
	public Comment mapRow(ResultSet rs, int rowNum) throws SQLException {
		Comment comment = new Comment();
		
		comment.setId(rs.getLong("id"));
		comment.setMember(new Member(rs.getLong("memberId")));
		comment.setPosting(new Posting(rs.getLong("postId")));
		comment.setContent(rs.getString("content"));
		comment.setRegDate(new Date(rs.getTimestamp("regDate").getTime()));

		return comment;
	}

}
