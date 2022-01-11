package pickmeal.dream.pj.posting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.java.Log;

@Controller
@Log
public class CommentController {
	
	@GetMapping("/posting/viewTogetherEatingComment")
	public ModelAndView viewTogetherEatingComment() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("posting/together_eating_comment");
		return mav;
	}
}
