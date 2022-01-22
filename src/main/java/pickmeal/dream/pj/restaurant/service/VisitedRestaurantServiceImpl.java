package pickmeal.dream.pj.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pickmeal.dream.pj.restaurant.domain.VisitedRestaurant;
import pickmeal.dream.pj.restaurant.repository.RestaurantDao;
import pickmeal.dream.pj.restaurant.repository.VisitedRestaurantDao;

@Service("visitedRestaurantService")
public class VisitedRestaurantServiceImpl implements VisitedRestaurantService{
	
	@Autowired
	VisitedRestaurantDao vrd;
	@Autowired
	RestaurantDao rd;

	@Override
	public void addVisitedRestaurant(VisitedRestaurant visitedRestaurant) {
		
		
	}

	@Override
	public List<VisitedRestaurant> findAllVisitedRestaurantByMemberId(long memberId) {
		
		return vrd.findAllVisitedRestaurantByMemberId(memberId);
	}

	@Override
	public VisitedRestaurant findVisitedRestaurantById(long id) {
		
		return vrd.findVisitedRestaurantById(id);
	}

	@Override
	public void removeVisitiedRestaurantById(long id) {
		vrd.removeVisitedRestaurantById(id);
	}

}
