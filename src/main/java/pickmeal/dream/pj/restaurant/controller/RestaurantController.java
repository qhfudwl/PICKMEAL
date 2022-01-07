package pickmeal.dream.pj.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RestaurantController {

	@GetMapping("/index")
	public ModelAndView index() {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
		
	}
}
