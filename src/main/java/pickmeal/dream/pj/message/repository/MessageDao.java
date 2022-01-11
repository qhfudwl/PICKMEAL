package pickmeal.dream.pj.message.repository;

import java.util.List;

import pickmeal.dream.pj.message.domain.Message;

public interface MessageDao {
	
	public List<Message> getMessageByType(char type);
}
