package pickmeal.dream.pj.restaurant.service;

import java.util.List;

import pickmeal.dream.pj.restaurant.domain.FavoriteRestaurant;
import pickmeal.dream.pj.restaurant.domain.Restaurant;

public interface FavoriteRestaurantSerivce {
	
	FavoriteRestaurant findFavoriteRestaurantById(long id);
	
	List<FavoriteRestaurant> findFavoriteRestaurantBymemberId(long memberId);
	
	void deleteFavoriteRestaurantById(long id);
	
	void addFavoriteRestaurant(FavoriteRestaurant favoriteRestaurnat);
	
	Restaurant findRestaurantById(long id);
	
}
