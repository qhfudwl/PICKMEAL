package pickmeal.dream.pj.message.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pickmeal.dream.pj.message.domain.Message;

@Repository("MessageDao")
public class MessageDaoImpl implements MessageDao{
	
	@Autowired
	JdbcTemplate jt;
	
	@Override
	public List<Message> getMessageByType(char type) {
		String sql ="SELECT id, messageType, content FROM Message WHERE messageType=?";
		return jt.query(sql, new MessageRowMapper(), String.valueOf(type));
	}

}
