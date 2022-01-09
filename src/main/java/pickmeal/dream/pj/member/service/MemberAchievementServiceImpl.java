package pickmeal.dream.pj.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pickmeal.dream.pj.member.dao.MemberAchievementDao;
import pickmeal.dream.pj.member.domain.FoodPowerPointItem;
import pickmeal.dream.pj.member.domain.Member;

@Service("memberAchievementService")
public class MemberAchievementServiceImpl implements MemberAchievementService {
	
	@Autowired
	private MemberAchievementDao mad;

	@Override
	public void addFoodPowerPointItem(FoodPowerPointItem fppi) {
		mad.addFoodPowerPointItem(fppi);
	}

	@Override
	public List<FoodPowerPointItem> findFoodPowerPointRecordByMemberId(long memberId) {
		return mad.findFoodPowerPointRecordByMemberId(memberId);
	}

	@Override
	public void addMannerTemperature(Member member) {
		mad.addMannerTemperature(member);
	}

	@Override
	public double findMannerTemperatureByMemberId(long memberId) {
		return mad.findMannerTemperatureByMemberId(memberId);
	}

}
