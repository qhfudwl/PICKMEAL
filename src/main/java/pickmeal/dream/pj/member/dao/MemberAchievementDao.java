package pickmeal.dream.pj.member.dao;

import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.web.constant.SavingPointConstants;

public interface MemberAchievementDao {
	public void addFoodPowerPointItem(Member member, SavingPointConstants spc);
}
