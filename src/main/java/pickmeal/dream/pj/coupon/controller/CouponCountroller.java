package pickmeal.dream.pj.coupon.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import pickmeal.dream.pj.coupon.domain.CouponCategory;
import pickmeal.dream.pj.coupon.service.CouponService;

@Controller
public class CouponCountroller {
	
	@Resource(name="couponService")
	CouponService cs;
	
	@GetMapping("/couponGenerate")
	public ModelAndView CouponGenerate() {
		
		ModelAndView mav = new ModelAndView();
		//mav.addObject("coupon",coupon);
		mav.setViewName("menu/coupongeneratetest");
		
		return mav;
		
	}
	@GetMapping("/couponGeneric")
	public ModelAndView CouponGeneric() {
		CouponCategory coupon = cs.findCouponCategoryTest();
		ModelAndView mav = new ModelAndView();
		mav.addObject("coupon",coupon);
		mav.setViewName("menu/generatesuccess");
		return mav;
		
	}
	
	
	
}
