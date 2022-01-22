package pickmeal.dream.pj.restaurant.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.restaurant.domain.FavoriteRestaurant;
import pickmeal.dream.pj.restaurant.domain.Restaurant;

public class FavoriteRestaurantRowMapper implements RowMapper<FavoriteRestaurant>{

	@Override
	public FavoriteRestaurant mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		FavoriteRestaurant favoriteRestaurant = new FavoriteRestaurant();
		
		favoriteRestaurant.setId(rs.getLong("id"));
		
		favoriteRestaurant.setMember(new Member(rs.getLong("memberId")));
		
		favoriteRestaurant.setRestaurant(new Restaurant(rs.getLong("restaurantId")));
		
		
		return favoriteRestaurant;
	}

}
