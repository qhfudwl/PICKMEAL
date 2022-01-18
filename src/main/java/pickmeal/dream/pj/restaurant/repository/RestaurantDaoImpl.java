package pickmeal.dream.pj.restaurant.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pickmeal.dream.pj.restaurant.domain.Restaurant;

@Repository("restaurantDao")
public class RestaurantDaoImpl implements RestaurantDao {
	
	@Autowired
	JdbcTemplate jt;
	@Override
	public Restaurant findRestaurantByAddress(Restaurant restaurant) {
		String sql = "SELECT id, rType, lat, lng, address, rName"
				+ " FROM Restaurant WHERE address = ?";
		restaurant = jt.queryForObject(sql, new RestaurantRowMapper(), restaurant.getAddress()); 
		
		return restaurant;
	}

	@Override
	public Restaurant findRestaurantByrType(Restaurant restaurant) {
		String sql = "SELECT id, rType, lat, lng, address, rName"
				+ " FROM Restaurant WHERE address = ?";
		restaurant = jt.queryForObject(sql, new RestaurantRowMapper(), restaurant.isRType());
		return restaurant;
	}

	@Override
	public Restaurant findRestaurantById(long id) {
		
		String sql = "SELECT id, apiId, rType, lat, lng, address, rName"
				+ " FROM Restaurant WHERE Id = ?";
		Restaurant restaurant = jt.queryForObject(sql, new RestaurantRowMapper(), id);
		return restaurant;
	}

}
