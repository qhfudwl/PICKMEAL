package pickmeal.dream.pj.message.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pickmeal.dream.pj.message.repository.MessageDao;

@Service("messageService")
public class MessageServiceImpl implements MessageService{
	@Autowired
	MessageDao msgd;

	// 매일 첫 로그인 후 게임하기 눌렀을 때 팝업에 띄워줄 메세지
	public String bringFirstMsg() {
		String firstMsg = msgd.bringFirstMsg('F');
		return firstMsg;
	}
	
	
}
