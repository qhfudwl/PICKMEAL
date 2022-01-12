package pickmeal.dream.pj.posting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.java.Log;
import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.posting.command.CommentCommand;
import pickmeal.dream.pj.posting.domain.Comment;
import pickmeal.dream.pj.posting.domain.Posting;
import pickmeal.dream.pj.posting.service.CommentService;

@Controller
@Log
public class CommentController {
	
	@Autowired
	CommentService cs;
	
	@GetMapping("/posting/viewTogetherEatingComment")
	public ModelAndView viewTogetherEatingComment() {
		ModelAndView mav = new ModelAndView();
		log.info("category 테스트로 넣어놓은 것 반드시 삭제할 것");
		List<Comment> comments = cs.findAllCommentByPostId(1, 'R');
		log.info(comments.toString());
		for(Comment c : comments) {
			log.info(c.toString());
		}
		mav.addObject("comments", comments);
		mav.setViewName("posting/together_eating_comment");
		return mav;
	}
	
	@PostMapping("/posting/addComment")
	public ResponseEntity<?> addComment(@ModelAttribute CommentCommand cc) {
		// comment 객체 셋팅
		Comment comment = new Comment();
		comment.setMember(new Member(cc.getMemberId()));
		comment.setPosting(new Posting(cc.getPostId(), cc.getCategory()));
		comment.setContent(cc.getContent());
		log.info(comment.toString());
		// comment 등록
		comment = cs.addComment(comment);
		log.info(comment.toString());
		log.info("category 테스트로 넣어놓은 것 반드시 삭제할 것");
		comment.getPosting().setCategory('R'); // test 값이다 (나중에 반드시 삭제하자!!)

		return ResponseEntity.ok(comment);
	}
}
