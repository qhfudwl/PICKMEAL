package pickmeal.dream.pj.restaurant.repository;

import java.util.List;
import java.util.Map;

import pickmeal.dream.pj.restaurant.domain.Restaurant;

public interface RestaurantCheckDao {
	public void insertResaurant(Restaurant res);
	
	public Restaurant selectRestaurant(Restaurant res);
}
