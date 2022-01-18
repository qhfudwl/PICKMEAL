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
	
	// 매일 첫 로그인 후 게임하기 눌렀을 때 팝업에 띄워줄 메세지
	public String bringFirstMsg() {
		String firstMsg = md.bringFirstMsg('F');
		return firstMsg;
	}
	
}
