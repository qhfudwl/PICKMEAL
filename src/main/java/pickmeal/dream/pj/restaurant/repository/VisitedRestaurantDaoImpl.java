package pickmeal.dream.pj.restaurant.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pickmeal.dream.pj.restaurant.domain.VisitedRestaurant;

@Repository("visitedRestaurantDao")
public class VisitedRestaurantDaoImpl implements VisitedRestaurantDao{
	
	@Autowired
	JdbcTemplate jt;
	@Override
	public List<VisitedRestaurant> findAllVisitedRestaurantByMemberId(long memberId) {
		String sql = "SELECT id, memberId, restaurantId, Review, regDate FROM VisitedRestaurant WHERE memberId = ?";
		
		return jt.query(sql, new VisitedRestaurantRowMapper(),memberId);
	}

	@Override
	public void addVisitedRestaurant(VisitedRestaurant visitedRestaurant) {
		String sql = "INSERT INTO VisitedRestaurant(memberId, restaurantId) VALUES(?,?)";
		
		jt.update(sql,visitedRestaurant.getMember().getId(),visitedRestaurant.getRestaurant().getId());
		
	}

	@Override
	public VisitedRestaurant findVisitedRestaurantById(long id) {
		String sql = "SELECT id, memberId, restaurantId, Review, regDate FROM VisitedRestaurant WHERE id = ?";
		
		return jt.queryForObject(sql, new VisitedRestaurantRowMapper(), id);
	}

	@Override
	public void writeVisitedRestaurantReviewById(long id) {
		String sql = "UPDATE VisitedRestaurant SET Review = true WHERE id = ?;";
		jt.update(sql,id);
		
	}
	
	

}
