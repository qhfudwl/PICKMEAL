package pickmeal.dream.pj.restaurant.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pickmeal.dream.pj.restaurant.domain.Restaurant;

public class RestaurantCheckRowMapper implements RowMapper<Restaurant>{

	@Override
	public Restaurant mapRow(ResultSet rs, int rowNum) throws SQLException {
		Restaurant restaurant = new Restaurant();
		restaurant.setId(rs.getLong("id"));
		restaurant.setRType(rs.getBoolean("rType"));
		
		return restaurant;
	}
	
}
