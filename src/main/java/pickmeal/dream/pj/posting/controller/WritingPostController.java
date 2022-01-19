package pickmeal.dream.pj.posting.controller;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import pickmeal.dream.pj.posting.command.WritingPostCommand;

@Controller
public class WritingPostController {

	
	@GetMapping("post_write")
	public ModelAndView writingPostMain(HttpServletRequest request) {
		//System.out.println("this is root "+ request.getSession().getServletContext().getRealPath("/"));
		ModelAndView mav = new ModelAndView();
		mav.setViewName("posting/post_write");
		return mav;
	}
	
	@GetMapping("post_writeT")
	public ModelAndView writingPostMain2(HttpServletRequest request) {
		//System.out.println("this is root "+ request.getSession().getServletContext().getRealPath("/"));
		ModelAndView mav = new ModelAndView();
		mav.setViewName("posting/writingPost_temp");
		return mav;
	}
	
	
	
	
	
	
	
	
	@PostMapping("completeWritingPost")
	public ModelAndView completeWritingPost(@ModelAttribute WritingPostCommand wpc) {
		
        ModelAndView mav = new ModelAndView();
 
		
		mav.addObject("content", wpc.getTime());
		mav.setViewName("posting/readingPost_temp");
		return mav;
	}
	
}
