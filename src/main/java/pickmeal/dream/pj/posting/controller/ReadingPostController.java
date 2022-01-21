package pickmeal.dream.pj.posting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.java.Log;
import pickmeal.dream.pj.posting.service.PostingService;

/**
 * 
 * @author 윤효심
 *
 */
@Controller
@Log
public class ReadingPostController {
	
	@Autowired
	PostingService ps;
	
	@GetMapping("/post_read")
	public ModelAndView readingPostMain(@RequestParam String id, @RequestParam char type) {
		
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("posting/post_read");
		return mav;
	}
}
