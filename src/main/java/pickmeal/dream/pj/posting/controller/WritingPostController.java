package pickmeal.dream.pj.posting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import pickmeal.dream.pj.posting.command.WritingPostCommand;

@Controller
public class WritingPostController {
	
	@GetMapping("writing")
	public ModelAndView writingPostMain() {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("posting/writingPost");
		return mav;
	}
	
	@PostMapping("completeWritingPost")
	public ModelAndView completeWritingPost(@ModelAttribute WritingPostCommand wpc) {
		System.out.println("here"+wpc.getTime());
		ModelAndView mav = new ModelAndView();
		mav.addObject("content", wpc.getTime());
		mav.setViewName("posting/readingPost");
		return mav;
	}
	
}
