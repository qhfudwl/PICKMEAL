package pickmeal.dream.pj.chat.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import pickmeal.dream.pj.chat.domain.Chat;
import pickmeal.dream.pj.member.domain.Member;

public class ChatRowMapper implements RowMapper<Chat> {

	@Override
	public Chat mapRow(ResultSet rs, int rowNum) throws SQLException {
		Chat chat = new Chat();
		chat.setId(rs.getLong("id"));
		chat.setWriter(new Member(rs.getLong("writerId")));
		chat.setCommenter(new Member(rs.getLong("commenterId")));
		chat.setMember(new Member(rs.getLong("memberId")));
		chat.setRegDate(new Date(rs.getTimestamp("regDate").getTime()));

		return chat;
	}

}
