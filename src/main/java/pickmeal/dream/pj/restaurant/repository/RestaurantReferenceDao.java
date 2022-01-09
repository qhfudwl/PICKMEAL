package pickmeal.dream.pj.restaurant.repository;

import pickmeal.dream.pj.restaurant.domain.RestaurantReference;

/**
 * 
 * 레스토랑 그래프
 * @author 윤효심
 *
 */
public interface RestaurantReferenceDao {
	
	/**
	 * 해당 레스토랑 ID를 가지고, 레스토랑의 성별 정보를 가져온다
	 * @param restaurantId
	 * @return
	 */
	public RestaurantReference getRestaurantReference(long restaurantId);

}
