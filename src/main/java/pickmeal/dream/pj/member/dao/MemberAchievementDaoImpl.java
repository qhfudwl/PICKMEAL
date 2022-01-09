package pickmeal.dream.pj.member.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pickmeal.dream.pj.member.domain.FoodPowerPointItem;
import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.web.constant.SavingPointConstants;

@Repository("memberAchievementDao")
public class MemberAchievementDaoImpl implements MemberAchievementDao {
	
	@Autowired
	JdbcTemplate jt;

	@Override
	public void addFoodPowerPointItem(FoodPowerPointItem fppi) {
		String sql = "INSERT INTO FoodPowerPoint(memberId, point, detail)"
				+ " VALUES (?, ?, ?)";
		
		jt.update(sql, fppi.getMember().getId(), fppi.getPoint(), fppi.getDetail().getNumber());
	}

	@Override
	public List<FoodPowerPointItem> findFoodPowerPointRecordByMemberId(long memberId) {
		String sql = "SELECT id, point, detail, regDate"
				+ " FROM FoodPowerPoint WHERE memberId=?";
		return jt.query(sql, new FoodPowerPointItemRowMapper(), memberId);
	}

	@Override
	public void addMannerTemperature(Member member) {
		String sql = "INSERT INTO MannerTemperature(memberId, temperature)"
				+ " VALUES (?, ?)";
		jt.update(sql, member.getId(), member.getMannerTemperature());
	}

	@Override
	public double findMannerTemperatureByMemberId(long memberId) {
		String sql = "SELECT temperature FROM MannerTemperature WHERE memberId=?";
		
		return jt.queryForObject(sql, Double.class, memberId);
	}
}
