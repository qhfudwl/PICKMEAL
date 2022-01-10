package pickmeal.dream.pj.coupon.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pickmeal.dream.pj.coupon.domain.Coupon;
import pickmeal.dream.pj.coupon.domain.CouponCategory;
import pickmeal.dream.pj.coupon.repository.CouponDao;
import pickmeal.dream.pj.restaurant.domain.Restaurant;
import pickmeal.dream.pj.restaurant.repository.RestaurantDao;

@Service("couponService")
public class CouponServiceImpl implements CouponService {
	
	@Autowired
	private RestaurantDao rd;
	@Autowired
	private CouponDao cd;
	
	public CouponServiceImpl() {
		
	}
	
	
	/**
	 * 쿠폰 발생 시켜주기!
	 */
	@Override
	public CouponCategory findCouponCategoryByAddress(Restaurant restaurant) {
		
		Restaurant restaurant2  = rd.findRestaurantByAddress(restaurant);
		/*가게가 제휴 가게인지 일단 확인하기*/
		if(restaurant2.isRType() == true) {
			
			/*쿠폰 발생확률 10000/1*/
			Random coupon = new Random();
			int coupongenerate = coupon.nextInt(100000);
			
			/*발생확률 성공시*/
			if(coupongenerate <=10) {
				char couponType;
				System.out.println("쿠폰발생 시켜주기");
				/* 쿠폰 타입 랜덤으로 정해주기 */
				Random couponTypeInt = new Random();
				if(couponTypeInt.nextInt(3) == 0) {
					couponType = 'A';
				}else if(couponTypeInt.nextInt(3) == 1) {
					couponType = 'B';
				}else{
					couponType = 'C';
				}
				/*쿠폰 타입으로 쿠폰 발행 하기*/
				CouponCategory couponCategory = cd.generateCouponTypeByRestaurant(couponType);
				return couponCategory;
			}
		
		}
		return null;
	}


	@Override
	public CouponCategory findCouponCategoryTest() {
		Random coupon = new Random();
		int coupongenerate = coupon.nextInt(100000);
		
		/*발생확률 성공시*/
		if(coupongenerate <=50000) {
			char couponType;
			System.out.println("쿠폰발생 시켜주기");
			/* 쿠폰 타입 랜덤으로 정해주기 */
			Random couponTypeInt = new Random();
			if(couponTypeInt.nextInt(3) == 0) {
				couponType = 'A';
			}else if(couponTypeInt.nextInt(3) == 1) {
				couponType = 'B';
			}else{
				couponType = 'C';
			}
			/*쿠폰 타입으로 쿠폰 발행 하기*/
			System.out.println(couponType);
			CouponCategory couponCategory = cd.generateCouponTypeByRestaurant(couponType);
			return couponCategory;
		}else {
			//System.out.println(coupongenerate);
			//System.out.println("발생이 안되썽");
			return null;
		}
	}


	@Override
	public Coupon addCoupon(CouponCategory couponCategory) {
		// TODO Auto-generated method stub
		return null;
	}

}
