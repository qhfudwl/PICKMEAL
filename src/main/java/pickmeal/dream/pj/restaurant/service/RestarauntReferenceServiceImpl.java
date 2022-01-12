package pickmeal.dream.pj.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pickmeal.dream.pj.restaurant.domain.RestaurantReference;
import pickmeal.dream.pj.restaurant.repository.RestaurantReferenceDao;

@Service("restarauntReferenceService")
public class RestarauntReferenceServiceImpl implements RestaurantReferenceService {
	
	@Autowired
	RestaurantReferenceDao rrd;

	@Override
	public RestaurantReference getRestaurantReference(long restaurantId) {
		return rrd.getRestaurantReference(restaurantId);
	}
	
	
}
