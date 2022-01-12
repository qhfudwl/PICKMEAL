package pickmeal.dream.pj.message.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pickmeal.dream.pj.message.domain.Message;
import pickmeal.dream.pj.message.repository.MessageDao;

@Service("messageService")
public class MessageServiceImpl implements MessageService{
	
	@Autowired
	MessageDao md;

	@Override
	public String getMessageByType(char type) {
		List<Message> mList = md.getMessageByType(type);
		
		Random random = new Random();
		int i = random.nextInt(mList.size());
		return mList.get(i).getContent();
	}
	
	
}
