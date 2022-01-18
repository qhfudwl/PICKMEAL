package pickmeal.dream.pj.restaurant.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.restaurant.domain.Restaurant;
import pickmeal.dream.pj.restaurant.domain.VisitedRestaurant;

public class VisitedRestaurantRowMapper implements RowMapper<VisitedRestaurant>{

	@Override
	public VisitedRestaurant mapRow(ResultSet rs, int rowNum) throws SQLException {
		VisitedRestaurant visitedRestaurant = new VisitedRestaurant();
		
		visitedRestaurant.setId(rs.getLong("id"));
		visitedRestaurant.setMember(new Member(rs.getLong("memberId")));
		visitedRestaurant.setRestaurant(new Restaurant(rs.getLong("restaurantId")));
		visitedRestaurant.setReview(rs.getBoolean("Review"));
		visitedRestaurant.setRegDate(rs.getDate("regDate"));
		return visitedRestaurant;
	}

}
