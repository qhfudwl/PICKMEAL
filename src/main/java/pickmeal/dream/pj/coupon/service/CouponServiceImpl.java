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
	public CouponCategory findCouponCategoryByRestaurant(Restaurant restaurant) {
			
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

	/**
	 * 쿠폰 발생 시켜주기 테스트!
	 */
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

	/**
	 * 쿠폰 발생후 쿠폰 등록 해주기
	 */
	@Override
	public Coupon addCoupon(Coupon coupon) {
		Random rnd =new Random();

		StringBuffer buf =new StringBuffer();
		String couponNum = null;
		do {
			for(int i=0;i<13;i++){

			    // rnd.nextBoolean() 는 랜덤으로 true, false 를 리턴. true일 시 랜덤 한 소문자를,
				//false 일 시 랜덤 한 숫자를 StringBuffer 에 append 한다.
				//rnd.nextBoolean()
			    if(rnd.nextBoolean()){
			    	//대문자 변환 후 넣어주기
			        buf.append((char)((int)(rnd.nextInt(26))+65));
			        
			    }else{
			    	//0~9
			        buf.append((rnd.nextInt(10)));
			      
			    }
			    
			}
			couponNum = buf.toString();
		}while(cd.isCouponByCouponNumber(couponNum) == true);
		
		coupon.setCouponNumber(couponNum);
		cd.addCoupon(coupon);
		
		return coupon;
	}


	@Override
	public Restaurant findRestaurantById(long id) {
		Restaurant restaurant = rd.findRestaurantById(id);
		return restaurant;
	}

}
