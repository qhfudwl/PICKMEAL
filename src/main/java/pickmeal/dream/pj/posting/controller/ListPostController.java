package pickmeal.dream.pj.posting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.java.Log;
import pickmeal.dream.pj.posting.domain.Posting;
import pickmeal.dream.pj.posting.domain.TogetherEatingPosting;
import pickmeal.dream.pj.posting.service.PostingService;

/**
 * 
 * @author 윤효심
 *
 */
@Controller
@Log
public class ListPostController {
	
	@Autowired
	PostingService ps;
	
	/**
	 * 카테고리에 따른 게시판 불러오기
	 * 		1) 게시판 타입별로 구분해주기
	 * 
	 * @param page
	 * @param type
	 * @return
	 */

	@GetMapping("/post_list")
	public ModelAndView listPostMain(@RequestParam char type) {
		ModelAndView mav = new ModelAndView();
		
		//공지사항
		if(type=='N') {
			mav.addObject("postType", "NOTICE");
		}
		//식당추천
		else if (type=='R') {
			mav.addObject("postType", "RECOMMEND_RESTAURANT");
		}
		//밥친구
		else {
			mav.addObject("postType", "TOGETHER_EATING");
		}

		mav.setViewName("posting/post_list");
		return mav;
	}
	
	/**
	 * 카테고리별 게시글 목록 불러오기
	 * 		1) page에 따른 게시글들을 불러온다 
	 * @param page
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("/post_list")
	@ResponseBody
	public ResponseEntity<T> listPostMain(@RequestParam int page, @RequestParam char type) {
		
		int postCnt=0;
		
		//공지사항
		if(type=='N') {
			postCnt = ps.getPostingCountByCategory('N');
		}
		//식당추천
		else if (type=='R') {
			postCnt = ps.getPostingCountByCategory('R');
		}
		//밥친구
		else {
			postCnt = ps.getPostingCountByCategory('E');
			List<TogetherEatingPosting> tep;
			List<Posting> posting = ps.findPostingsPerPageByCategory(type, page);
			
			/*
			 * 타입이 다른 List들을 캐스팅 하는 법
			 * 		1) Object로 업캐스팅 한다
			 * 		2) List<...>로 다운캐스팅 한다
			 */
			tep = (List<TogetherEatingPosting>)(Object)posting;
			
			for(TogetherEatingPosting temp : tep) {
				System.out.println("id : "+temp.getId()+" category : "+temp.getCategory());
			}
 			
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("posting/post_list");
		return mav;
	}
	
	
	
	
	
	
	
}
