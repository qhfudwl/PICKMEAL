package pickmeal.dream.pj.member.service;

import java.util.List;

import pickmeal.dream.pj.member.domain.FoodPowerPointItem;
import pickmeal.dream.pj.member.domain.Member;

public interface MemberAchievementService {
	/**
	 * 해당 멤버의 식력 포인트 내역을 기록한다.
	 * @param fppi
	 */
	public void addFoodPowerPointItem(FoodPowerPointItem fppi);
	
	/**
	 * 해당 멤버의 모든 식력 포인트 내역 불러오기
	 * @param memberId
	 * @return
	 */
	public List<FoodPowerPointItem> findFoodPowerPointRecordByMemberId(long memberId);
	
	/**
	 * 해당 멤버의 신뢰 온도 업데이트
	 */
	public void addMannerTemperature(Member member);

	/**
	 * 해당 사용자의 매너 온도 찾기
	 * @param memberId
	 * @return
	 */
	public double findMannerTemperatureByMemberId(long memberId);
}
