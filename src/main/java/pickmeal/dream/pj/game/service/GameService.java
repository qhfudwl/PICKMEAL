package pickmeal.dream.pj.game.service;

import java.util.List;
import java.util.Map;

import pickmeal.dream.pj.restaurant.domain.Restaurant;

public interface GameService {
	
	public void insertLastGameRecord(long memberId, long resId);
	
	public int checkLastGameRecord(long memberId);
	
	public String[][] makeLadder(List<Restaurant> resList);
	
	public void makePositionOfOX(String[][] ladderList);

	// 모든 선택에 대한 경로를 map에 담는 함수. (map 의 키는 세로줄의 인덱스, 값은 경로들을 표시한 것.)
//	public Map<Integer, List<String>> makeRoute(String[][] ladder, Map<Integer, List<String>> setOfRoute);
	public void makeRoute(String[][] ladder, Map<Integer, List<String>> setOfRoute);
}
