package pickmeal.dream.pj.member.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.extern.java.Log;
import pickmeal.dream.pj.member.domain.FoodPowerPointItem;
import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.web.constant.SavingPointConstants;

@Repository("memberAchievementDao")
@Log
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

	@Override
	public void addAttendance(Member member) {
		String sql = "INSERT INTO Attendance(memberId) VALUES (?)";
		jt.update(sql, member.getId());
	}

	@Override
	public void updateAttendance(Member member) {
		String sql = "UPDATE Attendance SET attendance=?, regDate=CURRENT_TIMESTAMP()"
				+ " WHERE memberId=?";
		log.info(String.valueOf(member.getAttendance()));
		jt.update(sql, member.getAttendance(), member.getId());
	}

	@Override
	public int checkAttendance(long memberId) {
		String sql = "SELECT TIMESTAMPDIFF(DAY, a.regDate, CURDATE()) AS DIFF_DAY"
				+ " FROM Attendance AS a WHERE memberId=?";
		return jt.queryForObject(sql, Integer.class, memberId);
	}

	@Override
	public int findAttendanceByMemberId(long memberId) {
		String sql = "SELECT attendance FROM Attendance WHERE memberId=?";
		
		return jt.queryForObject(sql, Integer.class, memberId);
	}

	@Override
	public int sumFoodPowerPoint(long memberId) {
		String sql = "SELECT SUM(point) FROM FoodPowerPoint WHERE memberId=?";

		return jt.queryForObject(sql, Integer.class, memberId);
	}
}
