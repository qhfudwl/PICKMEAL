package pickmeal.dream.pj.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RestaurantController {
	
	/*
	 *  메인화면 불러오기
	 * 
	 */
	@GetMapping("/index")
	public ModelAndView index() {
		
		/*연령별/성별별 데이터 불러오기*/
		
		
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
		
	}
	
	/**
	 * 
	 * 연령별/성별별 데이터 불러오기
	 * 
	 */
	
	
}
