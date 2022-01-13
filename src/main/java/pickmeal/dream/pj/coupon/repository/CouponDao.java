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
	
	CouponCategory findCouponCategoryByid(long id);
	
	List<Coupon> findAllCoupons();
	
	List<Coupon> findAllCouponsByMeneberId(long memberId);
	
	void addCoupon(Coupon coupon);
	
	public boolean isCouponByCouponNumber(String CouponNumber);
	
	void findAllCouponByusedAndregDate();
	
	Coupon changeUsedCouponById(long Id);
	
	List<Coupon> findUsedCouponsBymemberId(long memberId);
	
	List<Coupon> findUnusedCouponsBymemberId(long memberId);
	
	Coupon findCouponById(long id);
	
	Coupon findCouponByCouponNumber(String couponNumber);
	
	
	
	
	
	
}
