package pickmeal.dream.pj.game.repository;

import java.sql.Date;
import java.sql.Timestamp;

public interface GameDao{
	
	// 로그인하고 게임할 때 첫 게임인지 아닌지 확인하기 위해서 DB에 방문해야함.
	
	public void insertLastGameRecord(long memberId, long resId);
	
	public int checkLastGameRecord(long memberId);

}
