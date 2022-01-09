package pickmeal.dream.pj.restaurant.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import pickmeal.dream.pj.restaurant.domain.RestaurantReference;

public class RestaurantReferenceRowMapper implements RowMapper<RestaurantReference>{

	@Override
	public RestaurantReference mapRow(ResultSet rs, int rowNum) throws SQLException {
		List<Integer> ageCount = new ArrayList<Integer>();
		ageCount.add(rs.getInt("10s"));
		ageCount.add(rs.getInt("20s"));
		ageCount.add(rs.getInt("30s"));
		ageCount.add(rs.getInt("40s"));
		ageCount.add(rs.getInt("50s"));
		ageCount.add(rs.getInt("60s"));
		
		RestaurantReference restReference = new RestaurantReference(
				rs.getLong("restaurantId"),
				rs.getInt("femaleCount"),
				rs.getInt("maleCount"),
				ageCount);
		
		return restReference;
	}

	
	
	

}
