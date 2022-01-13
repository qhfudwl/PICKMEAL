package pickmeal.dream.pj.game.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;
import pickmeal.dream.pj.game.repository.GameDao;
import pickmeal.dream.pj.web.util.PresentTime;

@Service("GameService")
@Log
public class GameServiceImpl implements GameService{

	@Autowired
	GameDao gd;
	
	@Autowired
	PresentTime pt;

	public void insertLastGameRecord(long memberId, long resId) {
		// TODO Auto-generated method stub
		Timestamp now = pt.bringPresentTime();
		log.info("현재시간 to timestamp : " + now);
		
		gd.insertLastGameRecord(memberId, resId);
		
	}
	
	public int checkLastGameRecord(long memberId) {
		int diffOfDate = 0;
		
		diffOfDate = gd.checkLastGameRecord(memberId);
		
		return diffOfDate;
	}
	
	
}
