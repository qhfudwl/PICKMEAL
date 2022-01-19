package pickmeal.dream.pj.restaurant.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pickmeal.dream.pj.restaurant.domain.Restaurant;

@Repository("RestaurantCheckDao")
public class RestaurantCheckDaoImpl implements RestaurantCheckDao{

	@Autowired
	JdbcTemplate jt;


	@Override
	public void insertResaurant(Restaurant res) {
		// apiId로 비교하여 테이블에 없으면 insert, 있으면 무시.
		String sql = "INSERT IGNORE INTO Restaurant(lat, lng, address, rName, apiId) VALUES(?, ?, ?, ?, ?)";
		
		jt.update(sql, res.getLat(), res.getLng(), res.getAddress(), res.getRName(), res.getApiId());
	}


	@Override
	public Restaurant selectRestaurant(Restaurant res) {
		String sql = "SELECT id, rType FROM Restaurant WHERE apiId = ?";
				
		return jt.queryForObject(sql, new RestaurantCheckRowMapper(), res.getApiId());
	}
	
}
