package pickmeal.dream.pj.restaurant.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pickmeal.dream.pj.restaurant.domain.Review;

public class ReviewRowMapper implements RowMapper<Review> {

	@Override
	public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
		Review review = new Review();
		review.setRestaurantId(rs.getLong("restaurantId"));
		review.setUserCount(rs.getInt("userCount"));
		
		//추후수정가능
		review.setBathroom(rs.getInt("bathroom"));
		review.setKind(rs.getInt("kind"));
		review.setSpecialDay(rs.getInt("specialDay"));
		review.setClean(rs.getInt("clean"));
		review.setParking(rs.getInt("parking"));
		review.setGoodgroup(rs.getInt("goodgroup"));
		review.setAlone(rs.getInt("alone"));
		review.setBig(rs.getInt("big"));
		review.setInterior(rs.getInt("interior"));
		
		
		return review;
	}

}
