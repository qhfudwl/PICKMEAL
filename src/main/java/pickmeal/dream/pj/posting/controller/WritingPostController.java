package pickmeal.dream.pj.posting.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.java.Log;
import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.posting.command.PostingCommand;
import pickmeal.dream.pj.posting.domain.Posting;
import pickmeal.dream.pj.posting.service.PostingService;
import pickmeal.dream.pj.restaurant.domain.Restaurant;
/**
 * 
 * @author 윤효심	
 *
 */

@Controller
@Log
public class WritingPostController {
	
	/**
	 * 글쓰기 폼으로 이동
	 * 		- 각 게시판에서 글쓰기 버튼을 누를때, 
	 * 			redirect:/post_write?type=NOTICE 이렇게 불러주면
	 * 			게시판별로 다른 글쓰기 폼을 열어 줄 수 있다
	 * 	
	 * @param request
	 * @return
	 */
	
	@Autowired
	PostingService ps;
	
	
	@GetMapping("/post_write")
	public ModelAndView writingPostMain(@RequestParam String type) {
		
		Posting posting = new Posting();
		posting.setMember(new Member(1));
		posting.setRestaurant(new Restaurant(1));
		posting.setCategory('R');
		posting.setTitle("this is title");
		posting.setContent("this is content");
		posting.setLikes(10);
		posting.setViews(20);

		ps.addPosting(posting);
		System.out.println("hi");
		
		
		
		
		ModelAndView mav = new ModelAndView();
		if(type.equals("NOTICE")) {
			mav.addObject("postType", "NOTICE");
		}else if(type.equals("REVIEW")){
			mav.addObject("postType", "REVIEW");
		}else {
			mav.addObject("postType", "TOGETHER");
		}
		mav.setViewName("posting/post_write");
		return mav;
	}

	/**
	 * 지울 예정
	 * @param request
	 * @return
	 */
	@GetMapping("post_writeT")
	public ModelAndView writingPostMain2(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("posting/writingPost_temp");
		return mav;
	}

	
	/**
	 * 글쓰기 완료
	 * 		1) 테이블에 포스팅 정보 저장
	 * 		2) 저장한 정보 다시 테이블에서 불러서 글 읽기 폼으로 전달
	 * @param pc
	 * @return
	 */
	@PostMapping("completeWritingPost")
	public ModelAndView completeWritingPost(@ModelAttribute PostingCommand pc) {

		
		log.info("hi complete"+pc.toString());
		
		
		
		
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("posting/post_read");
		return mav;
	}

}
