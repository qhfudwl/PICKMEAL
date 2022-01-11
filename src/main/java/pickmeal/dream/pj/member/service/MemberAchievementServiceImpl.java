package pickmeal.dream.pj.member.service;

import java.util.List;

import static pickmeal.dream.pj.web.constant.Constants.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pickmeal.dream.pj.member.domain.FoodPowerPointItem;
import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.member.repository.MemberAchievementDao;
import pickmeal.dream.pj.web.constant.SavingPointConstants;

@Service("memberAchievementService")
public class MemberAchievementServiceImpl implements MemberAchievementService {
	
	@Autowired
	private MemberAchievementDao mad;

	@Override
	public Member addFoodPowerPointItem(Member member, SavingPointConstants spc) {
		// 먼저 멤버에 식력 포인트를 적립하고
		member.saveFoodPowerPoint(spc);
		// 식력 포인트 객체를 만들어서
		FoodPowerPointItem fppi = new FoodPowerPointItem();
		fppi.setMember(member);
		fppi.setPoint(spc.getPoint());
		fppi.setDetail(spc);
		// 셋팅한 식력 포인트 추가
		mad.addFoodPowerPointItem(fppi);
		// 식력 포인트에 따른 프로필 이미지를 잡아주자
		member.makeProfileImgPath();
		
		return member;
	}

	@Override
	public List<FoodPowerPointItem> findFoodPowerPointRecordByMemberId(long memberId) {
		return mad.findFoodPowerPointRecordByMemberId(memberId);
	}

	@Override
	public void addMannerTemperature(Member member) {
		// 멤버에게 신뢰온도를 같이 셋팅해서 반환하자 (처음 추가는 항상 sign_up_manner 로)
		member.saveMannerTemperature(SIGN_UP_MANNER);
		// 테이블에 신뢰 온도 넣기
		mad.addMannerTemperature(member);
	}

	@Override
	public Member findMannerTemperatureByMemberId(Member member) {
		double mt = mad.findMannerTemperatureByMemberId(member.getId());
		member.setMannerTemperature(mt);
		return member;
	}

	@Override
	public void addAttendance(Member member) {
		// 출석 테이블에 찍고 (회원가입 시에는 항상 1일)
		mad.addAttendance(member);
	}

	@Override
	public void updateAttendance(Member member) {
		// 먼저 두 날짜의 차이를 반환해서 차이가 0일 경우 update 를 하면 안된다.
		int diff = checkAttendance(member.getId());
		if (diff == 0) {
			return;
		}
		mad.updateAttendance(member);
	}

	@Override
	public int checkAttendance(long memberId) {
		return mad.checkAttendance(memberId);
	}

	@Override
	public Member findAttendanceByMemberId(Member member) {
		int attendance = mad.findAttendanceByMemberId(member.getId());
		member.setAttendance(attendance);
		return member;
	}

	@Override
	public Member sumFoodPowerPoint(Member member) {
		member.setFoodPowerPoint(mad.sumFoodPowerPoint(member.getId()));
		return member;
	}

	@Override
	public Member doSettingMemberInfo(Member member) {
		// 식력 포인트 셋팅
		member = sumFoodPowerPoint(member);
		// 신뢰 온도 셋팅
		member = findMannerTemperatureByMemberId(member);
		// 출석률 셋팅
		member = findAttendanceByMemberId(member);
		return member;
	}

}
