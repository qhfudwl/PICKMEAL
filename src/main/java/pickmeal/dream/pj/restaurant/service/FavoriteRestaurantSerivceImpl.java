package pickmeal.dream.pj.restaurant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pickmeal.dream.pj.restaurant.domain.FavoriteRestaurant;
import pickmeal.dream.pj.restaurant.domain.Restaurant;
import pickmeal.dream.pj.restaurant.repository.FavoriteRestaurantDao;
import pickmeal.dream.pj.restaurant.repository.RestaurantDao;

@Service("favoriteRestaurantService")
public class FavoriteRestaurantSerivceImpl implements FavoriteRestaurantSerivce{
	
	@Autowired
	FavoriteRestaurantDao frd;
	@Autowired
	RestaurantDao rd;

	@Override
	public FavoriteRestaurant findFavoriteRestaurantById(long id) {
		FavoriteRestaurant fr = frd.findFavoriteRestaurantById(id);
		return fr;
	}

	@Override
	public List<FavoriteRestaurant> findFavoriteRestaurantBymemberId(long memberId) {
		List<FavoriteRestaurant> frlist = frd.findFavoriteRestaurantBymemberId(memberId);
		return frlist;
	}

	@Override
	public void deleteFavoriteRestaurantById(long id) {
		frd.deleteFavoriteRestaurantById(id);
		
	}

	@Override
	public void addFavoriteRestaurant(FavoriteRestaurant favoriteRestaurnat) {
		if(frd.isFavoriteRestaurant(favoriteRestaurnat.getMember().getId(), favoriteRestaurnat.getRestaurant().getId())) {
			
		}else {
			frd.addFavoriteRestaurant(favoriteRestaurnat);
		}
		
	}

	@Override
	public Restaurant findRestaurantById(long id) {
		
		return rd.findRestaurantById(id);
	}

	@Override
	public boolean isFavoriteRestaurant(long memberId, long restaurantId) {
		
		return frd.isFavoriteRestaurant(memberId, restaurantId);
	}
	
	
}
