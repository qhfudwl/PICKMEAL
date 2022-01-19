package pickmeal.dream.pj.message.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pickmeal.dream.pj.message.domain.Message;
	
public class MessageRowMapper implements RowMapper<Message>{

	@Override
	public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
		Message message = new Message(rs.getLong("id"),rs.getString("messageType").charAt(0),rs.getString("content"));
		return message;
	}
}
