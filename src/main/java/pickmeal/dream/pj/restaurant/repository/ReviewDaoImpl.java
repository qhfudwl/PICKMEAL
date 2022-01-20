package pickmeal.dream.pj.restaurant.repository;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pickmeal.dream.pj.restaurant.domain.Review;

@Repository("reviewDao")
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

	@Override
	public void setReview(Review r) {
		String sql = "UPDATE Review SET bathroom = ?, kind = ?, specialDay = ?, clean = ?,"
				+ " parking = ?, goodgroup = ?, alone = ?, big = ?,interior = ? WHERE restaurantId = ?";
		
		jt.update(sql,r.getReviewItem().get(0).getReviewCount(),r.getReviewItem().get(1).getReviewCount(),
				r.getReviewItem().get(2).getReviewCount(),r.getReviewItem().get(3).getReviewCount(),
				r.getReviewItem().get(4).getReviewCount(),r.getReviewItem().get(5).getReviewCount(),
				r.getReviewItem().get(6).getReviewCount(),r.getReviewItem().get(7).getReviewCount(),
				r.getReviewItem().get(8).getReviewCount(),r.getRestaurantId());
		
	}
}
