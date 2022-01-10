package pickmeal.dream.pj.coupon.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import pickmeal.dream.pj.coupon.service.CouponService;

@Controller
public class CouponCountroller {
	
	@Resource(name="couponService")
	CouponService cs;
	
	
}
