package pickmeal.dream.pj.posting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WritingPostController {
	
	@GetMapping("/writing")
	public ModelAndView writingPostMain() {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("posting/writingPost");
		return mav;
	}
}
