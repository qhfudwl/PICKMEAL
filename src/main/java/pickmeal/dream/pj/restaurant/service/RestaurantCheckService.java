package pickmeal.dream.pj.restaurant.service;

import java.util.List;
import java.util.Map;

import pickmeal.dream.pj.restaurant.domain.Restaurant;

public interface RestaurantCheckService {
	public List<Restaurant> bringResList(List<Map<String, Object>> resultMap);
	
	public void convertMaptoResObject(List<Map<String, Object>> resultMap);
}
