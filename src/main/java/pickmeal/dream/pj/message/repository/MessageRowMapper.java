package pickmeal.dream.pj.message.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pickmeal.dream.pj.message.domain.Message;

public class MessageRowMapper implements RowMapper<Message> {

	@Override
	public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
		Message message = new Message();
		
		message.setId(rs.getLong("id"));
		message.setContent(rs.getString("content"));
		message.setMessageType(rs.getString("messageType").charAt(0));
		
		return message; // 메세지만 필요하긴 한데.
	}

}
