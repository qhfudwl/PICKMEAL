package pickmeal.dream.pj.restaurant.repository;

import pickmeal.dream.pj.restaurant.domain.Review;

/**
 * 
 * 레스토랑 리뷰
 * @author 윤효심
 *
 */
public interface ReviewDao {

	/**
	 * 해당 레스토랑 ID를 가지고, 레스토랑의 리뷰 정보를 가져온다.
	 * @param restaurantId
	 * @return
	 */
	public Review getReview(long restaurantId);
}
