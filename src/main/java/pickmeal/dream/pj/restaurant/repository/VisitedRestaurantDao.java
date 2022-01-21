package pickmeal.dream.pj.restaurant.repository;

import java.util.List;

import pickmeal.dream.pj.restaurant.domain.VisitedRestaurant;

public interface VisitedRestaurantDao {
	
	List<VisitedRestaurant> findAllVisitedRestaurantByMemberId(long memberId);
	
	void addVisitedRestaurant(VisitedRestaurant visitedRestaurant);
	
	VisitedRestaurant findVisitedRestaurantById(long id);
	
	void writeVisitedRestaurantReviewById(long id);
	
	void removeVisitedRestaurantById(long id);
	
	boolean isVisitedRestaurantById(long id);
}
