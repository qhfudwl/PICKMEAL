package pickmeal.dream.pj.restaurant.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pickmeal.dream.pj.restaurant.domain.Restaurant;

public class RestaurantRowMapper implements RowMapper<Restaurant>{

	@Override
	public Restaurant mapRow(ResultSet rs, int rowNum) throws SQLException {
		Restaurant restaurant = new Restaurant(rs.getLong("id"), rs.getBoolean("rType"),
				rs.getDouble("lat"),rs.getDouble("lng"),rs.getString("address"),rs.getString("rName"));
		return restaurant;
	}
	
}
