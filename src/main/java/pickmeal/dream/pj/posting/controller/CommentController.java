package pickmeal.dream.pj.posting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.java.Log;
import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.posting.command.CommentCommand;
import pickmeal.dream.pj.posting.domain.Comment;
import pickmeal.dream.pj.posting.domain.Posting;
import pickmeal.dream.pj.posting.service.CommentService;
import pickmeal.dream.pj.web.util.Validator;

@Controller
@Log
public class CommentController {
	
	@Autowired
	CommentService cs;
	
	@Autowired
	Validator v;
	
	@GetMapping("/posting/viewTogetherEatingComment")
	public ModelAndView viewTogetherEatingComment() {
		ModelAndView mav = new ModelAndView();
		log.info("category 테스트로 넣어놓은 것 반드시 삭제할 것");
		List<Comment> comments = cs.findAllCommentByPostId(1, 'R');
//		log.info(comments.toString());
//		for(Comment c : comments) {
//			log.info(c.toString());
//		}
		mav.addObject("comments", comments);
		mav.setViewName("posting/together_eating_comment");
		return mav;
	}
	
	@PostMapping("/posting/addComment")
	public ResponseEntity<?> addComment(@ModelAttribute CommentCommand cc) {
		// 빈 문자열일 경우 그냥 돌려보냄
		if (v.isEmpty(cc.getContent())) {
			return ResponseEntity.ok("empty");
		}
		// comment 객체 셋팅
		Comment comment = new Comment();
		comment.setMember(new Member(cc.getMemberId()));
		comment.setPosting(new Posting(cc.getPostId(), cc.getCategory()));
		comment.setContent(cc.getContent());
		log.info(comment.toString());
		// comment 등록
		comment = cs.addComment(comment);
//		log.info(comment.toString());
		log.info("category 테스트로 넣어놓은 것 반드시 삭제할 것");
		comment.getPosting().setCategory('R'); // test 값이다 (나중에 반드시 삭제하자!!)

		return ResponseEntity.ok(comment);
	}
	
	// 댓글 수정하기
	@GetMapping(value="/posting/modifyComment", produces="application/text; charset=utf8")
	public ResponseEntity<?> modifyComment(@ModelAttribute CommentCommand cc
			, @RequestParam("beforeContent") String beforeContent){
//		log.info("id : " + String.valueOf(id));
//		log.info("content : " + content);
//		log.info("beforeContent : " + beforeContent);
		
		// 수정할 문자열이 빈 문자열일 경우 다시 반환
		if (v.isEmpty(cc.getContent())) {
			return ResponseEntity.ok(beforeContent);
		} else if (cc.getContent().equals(beforeContent)) {
			return ResponseEntity.ok(cc.getContent());
		}
		// comment 객체 만들기
		Comment comment = new Comment();
		comment.setId(cc.getId());
		comment.setContent(cc.getContent());
		comment.setPosting(new Posting(cc.getPostId(), cc.getCategory()));
		comment.setMember(new Member(cc.getMemberId()));
		
		comment = cs.updateComment(comment);
		
		return ResponseEntity.ok(comment.getContent());
	}
	
	// 삭제하기
	@GetMapping("/posting/deleteComment")
	public ResponseEntity<?> deleteComment(@ModelAttribute CommentCommand cc){
		// comment 객체 만들기
		Comment comment = new Comment();
		comment.setId(cc.getId());
		comment.setPosting(new Posting(cc.getPostId(), cc.getCategory()));
		comment.setMember(new Member(cc.getMemberId()));
		
		// 삭제하면서 boolean 값 보내기
		return ResponseEntity.ok(cs.deleteComment(comment));
	}
	
	// 삭제 확인 팝업 띄우기
	@GetMapping("/posting/checkDeleteComment")
	public String checkDeleteComment() {
		return "posting/check_delete_comment";
	}
}
