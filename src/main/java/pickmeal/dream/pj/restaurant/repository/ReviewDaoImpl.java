package pickmeal.dream.pj.restaurant.repository;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pickmeal.dream.pj.restaurant.domain.Review;

@Repository("ReviewDao")
public class ReviewDaoImpl implements ReviewDao{
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jt;
	
	@Override
	public Review getReview(long restaurantId) {
		String sql="SELECT restaurantId, userCount,"
				+" bathroom, kind, specialDay, clean,"
				+" parking, goodgroup, alone, big, interior"
				+" FROM Review"
				+" WHERE restaurantId=?";
		return jt.queryForObject(sql, new ReviewRowMapper(), restaurantId );
	}

	
}
