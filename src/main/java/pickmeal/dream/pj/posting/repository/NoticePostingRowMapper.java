package pickmeal.dream.pj.posting.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.posting.domain.Posting;

public class NoticePostingRowMapper implements RowMapper<Posting>{

	@Override
	public Posting mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Posting posting = new Posting();
		posting.setId(rs.getLong("id"));
		posting.setMember(new Member(rs.getLong("memberId")));
		posting.setCategory('N');
		posting.setTitle(rs.getString("title"));
		posting.setContent(rs.getString("content"));
		posting.setViews(rs.getInt("views"));
		posting.setRegDate(rs.getDate("regDate"));
		return posting;
	}

}
