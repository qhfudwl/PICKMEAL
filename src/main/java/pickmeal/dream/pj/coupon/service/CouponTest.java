package pickmeal.dream.pj.coupon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import pickmeal.dream.pj.config.DataSourceConfig;
import pickmeal.dream.pj.coupon.domain.Coupon;
import pickmeal.dream.pj.coupon.domain.CouponCategory;
import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.member.service.MemberService;
import pickmeal.dream.pj.restaurant.domain.Restaurant;


@Service("couponTest")
public class CouponTest {
	private static CouponService cs;
	private static MemberService ms;
	@Autowired
	public CouponTest(CouponService couponService) {
		this.cs = couponService;
	}
	
	public CouponTest() {
		
	}
	
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DataSourceConfig.class);
		CouponTest test = context.getBean("couponTest",CouponTest.class);
		List<Coupon> coupons = cs.findAllCouponsByMemberId(1);
		/*
		for(int i=0; i< coupons.size();i++) {
		//System.out.println(cs.findAllCouponsByMemberId(1).get(i).getRestaurant());
		}
		//System.out.println(coupons.size());
		//Restaurant restaurant = cs.findRestaurantById(cs.findAllCouponsByMemberId(1).get(1).getRestaurant().getId());
		//System.out.println(restaurant);
		for(Coupon c : coupons) {
			Restaurant restaurant = cs.findRestaurantById(c.getRestaurant().getId());
			CouponCategory couponCategory = cs.findCouponCategoryByid(c.getCouponCategory().getId());
			//Member member = ms.findMemberById(c.getMember().getId());
			//c.setMember(member);
			c.setRestaurant(restaurant);
			c.setCouponCategory(couponCategory);
			System.out.println(c.getRestaurant().getRName()+c.getCouponCategory().getCouponName()+c.getCouponNumber());
			
		}
		//Coupon coupon = cs.findAllCouponsByMemberId(1).get(1);
		//coupon.setRestaurant(restaurant);
		//System.out.println(coupon.getRestaurant().getRName()); 
		//System.out.println(cs.findRestaurantById(cs.findAllCouponsByMemberId(1).get(1).getRestaurant().getId()));
		*/
		System.out.println(cs.findCouponByMemberIdinToday(1));
			
		
		
	}
}
