package pickmeal.dream.pj.member.repository;

import java.util.List;

import pickmeal.dream.pj.member.domain.FoodPowerPointItem;
import pickmeal.dream.pj.member.domain.Member;

public interface MemberAchievementDao {
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
	
	/**
	 * 멤버의 첫 출석 (회원가입 시 자동 로그인이기 때문에 1일 출석을 바로 올린다.)
	 * @param member
	 */
	public void addAttendance(Member member);
	
	/**
	 * 멤버 연속 출석 수 업데이트 (로그인 시)
	 * @param member
	 */
	public void updateAttendance(Member member);
	
	/**
	 * 로그인 시 오늘 날짜와 전의 로그인 날짜를 비교해서 반환
	 * @return
	 */
	public int checkAttendance(long memberId);
	
	/**
	 * 멤버 출석 수 가져오기
	 * @param memberId
	 * @return
	 */
	public int findAttendanceByMemberId(long memberId);
}
