package pickmeal.dream.pj.game.service;

public interface GameService {
	
	public void insertLastGameRecord(long memberId, long resId);
	
	public int checkLastGameRecord(long memberId);
}
