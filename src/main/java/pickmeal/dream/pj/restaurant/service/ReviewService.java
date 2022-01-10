package pickmeal.dream.pj.restaurant.service;

import pickmeal.dream.pj.restaurant.domain.Review;
/**
 * 
 * @author 윤효심
 * 
 */
public interface ReviewService {

	/**
	 * 해당 레스토랑 ID를 가지고, 레스토랑의 리뷰 정보를 DAO에서 가져온다.
	 * @param restaurantId
	 * @return
	 */
	public Review getReview(long restaurantId);
}
