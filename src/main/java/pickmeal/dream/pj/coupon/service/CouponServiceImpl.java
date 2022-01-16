package pickmeal.dream.pj.coupon.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pickmeal.dream.pj.coupon.domain.Coupon;
import pickmeal.dream.pj.coupon.domain.CouponCategory;
import pickmeal.dream.pj.coupon.repository.CouponDao;
import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.member.repository.MemberDao;
import pickmeal.dream.pj.restaurant.domain.Restaurant;
import pickmeal.dream.pj.restaurant.repository.RestaurantDao;

@Service("couponService")
public class CouponServiceImpl implements CouponService {
	
	@Autowired
	private RestaurantDao rd;
	@Autowired
	private CouponDao cd;
	@Autowired
	private MemberDao md;
	
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

	/**
	 * 레스토랑 아이디로 레스토랑 찾아오기
	 */
	@Override
	public Restaurant findRestaurantById(long id) {
		Restaurant restaurant = rd.findRestaurantById(id);
		return restaurant;
	}

	/**
	 * 쿠폰 미사용 -> 사용으로 변경 해주기
	 */
	@Override
	public void changeUsedCouponById(long id) {
		cd.changeUsedCouponById(id);
	}

	/**
	 * 내가 받은 쿠폰 모두 출력해주기 (삭제된거 제외) ㅇ
	 */
	@Override
	public List<Coupon> findAllCouponsByMemberId(long memberId) {
		return cd.findAllCouponsByMeneberId(memberId);
	}
	
	/**
	 * 내가 사용한 쿠폰들 모두 보여주기 ㅇ
	 */
	@Override
	public List<Coupon> findUsedConponsByMemberId(long memberId) {
		List<Coupon> coupons = cd.findUsedCouponsBymemberId(memberId);
		//coupons.get(0)
		/*Member member = md.findMemberById(coupons.get(0).getMember().getId());
		coupons.get(0).setMember(member);
		Restaurant restaurant = rd.findRestaurantById(coupons.get(0).getRestaurant().getId());
		coupons.get(0).setRestaurant(restaurant);
		CouponCategory couponCategory = cd.findCouponCategoryByid(coupons.get(0).getCouponCategory().getId());
		coupons.get(0).setCouponCategory(couponCategory);*/
		System.out.println("들어오냐?");
		
		return cd.findUsedCouponsBymemberId(memberId);
	}

	/**
	 * 내가 사용 안한 쿠폰들 모두 보여주기 ㅇ
	 */
	@Override
	public List<Coupon> findUnusedCouponsByMemberId(long memberId) {
		return cd.findUnusedCouponsBymemberId(memberId);
	}

	/**
	 * 쿠폰 고유번호로 쿠폰 하나 찾아오기 
	 */
	@Override
	public Coupon findCouponById(long id) {
		
		return cd.findCouponById(id);
	}


	@Override
	public CouponCategory findCouponCategoryByid(long id) {
		
		return cd.findCouponCategoryByid(id);
	}


	@Override
	public Coupon findCouponByCouponNumber(String couponNumber) {
		
		return cd.findCouponByCouponNumber(couponNumber);
	}


	@Override
	public Integer findCouponBymemberIdinTodayMax(long memberId) {
		
		return cd.findCouponBymemberIdinTodayMax(memberId); 
	}


	@Override
	public int findCouponByMemberIdinToday(long memberId) {
		return cd.findCouponByMemberIdinToday(memberId);
	}
	

}
