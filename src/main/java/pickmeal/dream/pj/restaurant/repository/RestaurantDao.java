package pickmeal.dream.pj.restaurant.repository;

import pickmeal.dream.pj.restaurant.domain.Restaurant;

public interface RestaurantDao {
	
	/**
	 * 쿠폰 발급 시점
	 * @param address
	 * @return
	 */
	Restaurant findRestaurantByAddress(Restaurant restaurant);
	
	Restaurant findRestaurantByrType(Restaurant restaurant);
	
	Restaurant findRestaurantById(long id);
}
