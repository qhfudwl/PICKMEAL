package pickmeal.dream.pj.coupon.repository;

import java.util.Date;
import java.util.List;

import pickmeal.dream.pj.coupon.domain.Coupon;
import pickmeal.dream.pj.coupon.domain.CouponCategory;
import pickmeal.dream.pj.restaurant.domain.Restaurant;

public interface CouponDao {
	
	/**
	 * 쿠폰 발급 시점
	 * @param address
	 * @return
	 */
	
	CouponCategory generateCouponTypeByRestaurant(char couponType);
	
	List<Coupon> findAllCoupons();
	
	void removeNotUsedByRegDate(boolean used, Date regDate);
	
	List<Coupon> findAllCouponsByMeneberId(long memberId);
	
	void addCoupon(Coupon coupon);
	
	
	
	
	
	
	
}
