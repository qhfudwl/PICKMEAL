package pickmeal.dream.pj.message.service;


import pickmeal.dream.pj.message.domain.Message;


public interface MessageService {

	/**
	 * 메세지 리스트에서 랜덤한 메세지 1개를 리턴해준다
	 * @param type
	 * @return
	 * @author 윤효심
	 */
	public String getMessageByType(char type);
	
	public String bringFirstMsg();

}
