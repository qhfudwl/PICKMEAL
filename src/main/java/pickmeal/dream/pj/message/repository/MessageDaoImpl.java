package pickmeal.dream.pj.message.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pickmeal.dream.pj.message.domain.Message;

@Repository("messageDao")
public class MessageDaoImpl implements MessageDao{

	@Autowired
	JdbcTemplate jt;
	
	@Override
	public String bringFirstMsg(char msgType) {
		String sql = "SELECT content FROM Message WHERE messageType = ? ORDER BY rand() limit 1";
		Message msg = jt.queryForObject(sql, new MessageRowMapper(), msgType);
		
		return msg.getContent();
	}
	
}
// order by rand() limit 10