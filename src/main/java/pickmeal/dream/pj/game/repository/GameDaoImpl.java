package pickmeal.dream.pj.game.repository;

import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("GameDao")
public class GameDaoImpl implements GameDao{
	
	@Autowired
	JdbcTemplate jt;

	@Override
	public void insertLastGameRecord(long memberId, long resId) {
		String sql ="INSERT INTO LastGameRecord (memberId, restaurantId) VALUES (?, ?) "
				+ " ON DUPLICATE KEY UPDATE regDate = CURRENT_TIMESTAMP()";
		// 없으면 INSERT 있으면 UPDATE / memberId가 unique Key.
		
		jt.update(sql, memberId, resId); // 이렇게 인자 4개 넣어주면 되는지 확인.		
		
	}

	@Override
	public int checkLastGameRecord(long memberId) {
		String sql = "SELECT TIMESTAMPDIFF(DAY, regDate, CURDATE() AS DIFF_DAY FROM LastGameRecord WHERE memberId = ?";
		
		return jt.queryForObject(sql, Integer.class, memberId);
	}

}
