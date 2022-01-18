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
				+ " ON DUPLICATE KEY UPDATE regDate = CURRENT_TIMESTAMP(), restaurantId = ?";
		// 없으면 INSERT 있으면 UPDATE / memberId가 unique Key.
		
		System.out.println("insertLastGameRecord by DaoImpl");
		
		jt.update(sql, memberId, resId, resId); // 이렇게 인자 4개 넣어주면 되는지 확인.		
		
	}

	@Override
	public int checkLastGameRecord(long memberId) {
		// if는(a, b, c) a조건이 참이면 b 거짓이면 c의 결과를 반환한다. / exists 는 조건이 부합하면 1 아니면 0 이 반환된다.(확인할 것)  
		// 아래 쿼리문은 exists 구문이 0이면 참으로 0 을 반환, 거짓이면 select 이하 구문 고
		String sql = "SELECT IF((select EXISTS (select memberID from LastGameRecord where memberId = ?)) = 0 , 0, "
				+ "(SELECT TIMESTAMPDIFF(DAY, regDate, CURDATE()) AS DIFF_DAY FROM LastGameRecord WHERE memberId = ?))";
				//"SELECT TIMESTAMPDIFF(DAY, regDate, CURDATE() AS DIFF_DAY FROM LastGameRecord WHERE memberId = ?";
		//String sql2 = "Select count(regdate) from table group by memberId having regdate = curdate();" 
		
		return jt.queryForObject(sql, Integer.class, memberId, memberId);
	}

}
