package pickmeal.dream.pj.posting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.java.Log;
import pickmeal.dream.pj.posting.domain.Comment;
import pickmeal.dream.pj.posting.service.CommentService;

@Controller
@Log
public class CommentController {
	
	@Autowired
	CommentService cs;
	
	@GetMapping("/posting/viewTogetherEatingComment")
	public ModelAndView viewTogetherEatingComment() {
		ModelAndView mav = new ModelAndView();
		List<Comment> comments = cs.findAllCommentByPostId(40, 'E');
		for(Comment c : comments) {
			log.info(c.toString());
		}
		mav.addObject("comments", comments);
		mav.setViewName("posting/together_eating_comment");
		return mav;
	}
	
}
