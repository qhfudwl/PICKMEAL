package pickmeal.dream.pj.restaurant.service;

import java.util.List;

import pickmeal.dream.pj.restaurant.domain.VisitedRestaurant;

public interface VisitedRestaurantService {
	
	void addVisitedRestaurant(VisitedRestaurant visitedRestaurant);
	
	List<VisitedRestaurant> findAllVisitedRestaurantByMemberId(long memberId);
	
	VisitedRestaurant findVisitedRestaurantById(long id);
}
