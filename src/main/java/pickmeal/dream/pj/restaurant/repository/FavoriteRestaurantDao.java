package pickmeal.dream.pj.restaurant.repository;

import java.util.List;

import pickmeal.dream.pj.restaurant.domain.FavoriteRestaurant;

public interface FavoriteRestaurantDao {
	FavoriteRestaurant findFavoriteRestaurantById(long id);
	
	List<FavoriteRestaurant> findFavoriteRestaurantBymemberId(long memberId);
	
	void deleteFavoriteRestaurantById(long id);
	
	void addFavoriteRestaurant(FavoriteRestaurant favoriteRestaurant);
	
	
	
}
