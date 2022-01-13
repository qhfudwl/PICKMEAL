package pickmeal.dream.pj.coupon.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pickmeal.dream.pj.coupon.domain.Coupon;
import pickmeal.dream.pj.coupon.domain.CouponCategory;
import pickmeal.dream.pj.coupon.service.CouponService;
import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.restaurant.domain.Restaurant;
@Controller
public class CouponControllerByMyPage {
	
	@Resource(name="couponService")
	CouponService cs;
	
	/**
	 * 마이페이지 첫 화면
	 * @return
	 */
	@GetMapping("/mypageCC")
	public ModelAndView myPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("coupon/nav_by_coupons");
		return mav;
	}
	/**
	 * 모든쿠폰 조회
	 * @param session
	 * @return
	 */
	@GetMapping("/findAllCoupon")
	public ModelAndView findAllCoupon(HttpSession session) {
		List<Coupon> unusedcoupons = new ArrayList<Coupon>();
		List<Coupon> usedcoupons = new ArrayList<Coupon>();
		Member member = (Member) session.getAttribute("member");
		//미사용 쿠폰
		unusedcoupons = cs.findUnusedCouponsByMemberId(member.getId());
		for(Coupon c : unusedcoupons) {
			Restaurant restaurant = cs.findRestaurantById(c.getRestaurant().getId());
			CouponCategory couponCategory = cs.findCouponCategoryByid(c.getCouponCategory().getId());
			c.setRestaurant(restaurant);
			c.setCouponCategory(couponCategory);
		}
		//사용한 쿠폰
		usedcoupons = cs.findUsedConponsByMemberId(member.getId());
		for(Coupon c : usedcoupons) {
			Restaurant restaurant = cs.findRestaurantById(c.getRestaurant().getId());
			CouponCategory couponCategory = cs.findCouponCategoryByid(c.getCouponCategory().getId());
			c.setCouponCategory(couponCategory);
			c.setRestaurant(restaurant);
		}
		ModelAndView mav = new ModelAndView();
		//System.out.println(unusedcoupons);
		//System.out.println(unusedcoupons.get(0).getCouponCategory().getCouponName());
		//String Rname = unusedcoupons.get(0).getRestaurant().getRName(); 식당명
		//String Cname = unusedcoupons.get(0).getCouponCategory().getCouponName();
		//System.out.println(Cname);
        //unusedcoupons.get(${Status.index}).getCouponNumber(); 쿠폰번호
		//unusedcoupons.get(${Status.index}).getCouponCategory().getLimitPrice(); 쿠폰 제약사항
		//unusedcoupons.get(0).getRegDate(); 쿠폰 레그데이트
		mav.addObject("member",member);
		mav.addObject("unusedcoupons",unusedcoupons);
		mav.addObject("usedcoupons",usedcoupons);
		mav.setViewName("coupon/coupon_list");
		
		return mav;
	}
	
	/**
	 * 사용쿠폰 조회
	 * @param session
	 * @return
	 */
	/*
	@GetMapping("/findCoupon")
	public ModelAndView findUsedCoupon(HttpSession session) {
		List<Coupon> coupons = new ArrayList<Coupon>();
		Member member = (Member) session.getAttribute("member");
		coupons = cs.findUsedConponsByMemberId(member.getId());
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("coupons" ,coupons);
		mav.setViewName("마이페이지/사용쿠폰조회.jsp");
		return mav;
	}
	
	@GetMapping("/findUnusedCoupon")
	public ModelAndView findUnusedCoupon(HttpSession session) {
		List<Coupon> coupons = new ArrayList<Coupon>();
		Member member = (Member) session.getAttribute("member");
		coupons = cs.findUnusedCouponsByMemberId(member.getId());
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("coupons" ,coupons);
		mav.setViewName("마이페이지/미사용쿠폰조회.jsp");
		return mav;
	}
	/**
	 * 쿠폰 목록에서 쿠폰 하나 클릭 했을 때
	 * @return
	 */
	@GetMapping("/usedCouponPopup")
	public ModelAndView usedCouponPopup(@RequestParam("cId") long cId){
		System.out.println("팝업창 전달완료 : "+cId);
		Coupon coupon = cs.findCouponById(cId);
		System.out.println("쿠폰은 : "+coupon);
		Restaurant restaurant = cs.findRestaurantById(coupon.getRestaurant().getId());
		coupon.setRestaurant(restaurant);
		CouponCategory couponCategory = cs.findCouponCategoryByid(coupon.getCouponCategory().getId());
		coupon.setCouponCategory(couponCategory);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("coupon" ,coupon);
		mav.setViewName("coupon/coupon_popup");
		return mav;
	}
	
	/**
	 * 
	 * @return
	 */
	@PostMapping("/usedCoupon")
	public ModelAndView usedCoupon(@RequestParam("couponid") long couponid, HttpSession session) {
		System.out.println("팝업창에서 잘 보냄 : " + couponid);
		cs.changeUsedCouponById(couponid);
		
		Member member = (Member) session.getAttribute("member");
		List<Coupon> unusedcoupons = new ArrayList<Coupon>();
		List<Coupon> usedcoupons = new ArrayList<Coupon>();
		unusedcoupons = cs.findUnusedCouponsByMemberId(member.getId());
		for(Coupon c : unusedcoupons) {
			Restaurant restaurant = cs.findRestaurantById(c.getRestaurant().getId());
			CouponCategory couponCategory = cs.findCouponCategoryByid(c.getCouponCategory().getId());
			c.setRestaurant(restaurant);
			c.setCouponCategory(couponCategory);
			System.out.println("미사용 : " +c.getId() +c.getCouponNumber() +c.getRestaurant().getRName() + c.getCouponCategory().getCouponName());
		}
		//사용한 쿠폰
		usedcoupons = cs.findUsedConponsByMemberId(member.getId());
		for(Coupon c : usedcoupons) {
			Restaurant restaurant = cs.findRestaurantById(c.getRestaurant().getId());
			CouponCategory couponCategory = cs.findCouponCategoryByid(c.getCouponCategory().getId());
			c.setCouponCategory(couponCategory);
			c.setRestaurant(restaurant);
			System.out.println("사용 : " +c.getCouponNumber() +c.getRestaurant().getRName() + c.getCouponCategory().getCouponName());
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("usedcoupons",usedcoupons);
		mav.addObject("unusedcoupons",unusedcoupons);
		mav.setViewName("coupon/coupon_list");
		return mav;
		
		
		
		
		
		//return ResponseEntity.ok(usedCoupons, unUsedCoupons);
	}
}
