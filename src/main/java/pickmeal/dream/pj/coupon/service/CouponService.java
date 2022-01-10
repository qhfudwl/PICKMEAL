package pickmeal.dream.pj.coupon.service;

import pickmeal.dream.pj.coupon.domain.CouponCategory;
import pickmeal.dream.pj.restaurant.domain.Restaurant;

public interface CouponService {
	CouponCategory findCouponCategoryByAddress(Restaurant restaurant);
}
