package pickmeal.dream.pj.posting.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.java.Log;
import pickmeal.dream.pj.posting.domain.Posting;
import pickmeal.dream.pj.posting.domain.TogetherEatingPosting;
import pickmeal.dream.pj.posting.service.PostingService;
import pickmeal.dream.pj.posting.util.Criteria;
import pickmeal.dream.pj.posting.util.PageMaker;

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


	/* 
	 * 
	 * 이슈1) 
	 * 게시판별 변수 2개만 셋팅해줬을 뿐인데 이런 코드 길이가~~?
	 * !!!! 이렇게는 만들지 말자~! => 다중매핑사용
	 * 
	 * 
	 * 
	@GetMapping("/post_list/notice")
	public ModelAndView listPostNotice(@RequestParam int page) {
		ModelAndView mav = new ModelAndView();
		int postCnt=0;
		
		//총 게시물 수 ( 페이징 관련 )
		postCnt = ps.getPostingCountByCategory('N');
		
		//1page당 보여질 게시물
		List<Posting> listPost = ps.findPostingsPerPageByCategory('N', page);
		
		mav.addObject("postCnt", postCnt);
		mav.addObject("listPost", listPost);
		mav.setViewName("posting/post_list");
		return mav;
	}
	@GetMapping("/post_list/recommend")
	public ModelAndView listPostRecommendRestaurant(@RequestParam int page) {
		ModelAndView mav = new ModelAndView();
		int postCnt=0;
		
		//총 게시물 수 ( 페이징 관련 )
		postCnt = ps.getPostingCountByCategory('R');
		
		//1page당 보여질 게시물
		List<Posting> listPost = ps.findPostingsPerPageByCategory('R', page);
		
		mav.addObject("postCnt", postCnt);
		mav.addObject("listPost", listPost);
		mav.setViewName("posting/post_list");
		return mav;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/post_list/together")
	public ModelAndView listPostTogetherEating(@RequestParam int page) {
		ModelAndView mav = new ModelAndView();
		int postCnt=0;
		
		//총 게시물 수 ( 페이징 관련 )
		postCnt = ps.getPostingCountByCategory('E');
		
		//1page당 보여질 게시물
		List<Posting> listPostT = ps.findPostingsPerPageByCategory('E', page);
		//List 업캐스팅
		List<TogetherEatingPosting> listPost = (List<TogetherEatingPosting>)(Object)listPostT;
		
		mav.addObject("postCnt", postCnt);
		mav.addObject("listPost", listPost);
		mav.setViewName("posting/post_list");
		return mav;
	}
	*/
	
	
	
	/*
	 * 
	 * 
	 * 이슈2)  
	 * 		어떻게든 지저분하게 URI를 만들고 싶지 않다.
	 * 		그러나 무슨 게시판인지 type설정해줘야했다.
	 * 
	 * 		요구사항 
	 * 				:	URL을 읽고 어떻게는 무슨 게시판인지는 알게 해야겠음
	 *				ex)  http://localhost:8080/pickmeal/posting/notice
	 * 
	 * 		원래방법1) URL에 http://localhost:8080/pickmeal/posting/notice?page=1
	 * 				+) 코드에 아래와 같이 if문 8줄 추가 정말 싫음 
	 * 
	 * 		원래방법2) URL에 Criteria 클래스 안에 있는 type을 지정
	 * 				 http://localhost:8080/pickmeal/posting/notice?type=N&page=1
	 *				-> 게시판 카테고리 정보가 2번이나 들어가서 참을 수가 없음
	 
		//@GetMapping(value = {"/posting/notice", "/posting/recommend", "/posting/together"})
		public ModelAndView listPostView(HttpServletRequest request,Criteria criteria  ) {
			/*
			 * 게시판 카테고리별 셋팅해주기
			 */
			/*
			//현재 페이지 URI 불러오기( ex. /pickmeal/post_list/notice ) 
			String currentURI = request.getRequestURI();
			currentURI = currentURI.split("/")[3];
			
					
			if(currentURI.equals("notice")) {
				criteria.setType('N');
			}
			else if(currentURI.equals("recommend")){
				criteria.setType('R');
			}else {
				criteria.setType('E');
			}
		
	
			PageMaker pageMaker = new PageMaker(ps.getPostingCountByCategory(criteria.getType()),criteria);
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/posting/post_list");
			return mav;
			
			
			
		}
		
		*/
		
		
	/**
	 * 카테고리별 게시글 목록 불러오기
	 * 		1) @PathVariable을 이용한다
			2) 게시판 페이별 정보 읽어오는 것은 Criteria 클래스로 처리한다.
	 * 		3) 게시판 페이징 처리는 PageMaker 클래스로 처리한다.
	 * 
	 * 		조건
	 * 		1) 게시판 불러오기 / 게시판 작성 / 게시판 읽기 모두 같은 함수를 실행하도록 한다. => 코드의 재사용성을 높인다
	 * @param page
	 * @return
	 */
	@GetMapping("/posting/{type}")
	public ModelAndView listPostView(Criteria criteria, @PathVariable String type ) {
		ModelAndView mav = new ModelAndView();
		
		//게시판 카테고리별 셋팅 1줄로 가능!
		criteria.setType(type);
		
		//게시판 페이징 처리 클래스 셋팅하기
		PageMaker pageMaker = new PageMaker(ps.getPostingCountByCategory(criteria.getType()),criteria);
		log.info("PageMaker type : "+ pageMaker.getCriteria().getType()+" page : "+pageMaker.getCriteria().getPage()+" totalCnt : "+pageMaker.getTotal());
		mav.addObject("pageMaker", pageMaker);
		
		//게시물 불러오기
		List<Posting> postings = ps.findPostingsPerPageByCategory(pageMaker.getCriteria());
		mav.addObject("postings", postings);
		
		//게시판이 밥친구일 경우, 업캐스팅 해준다
		if(pageMaker.getCriteria().getType()=='E') {
			@SuppressWarnings("unchecked")
			List<TogetherEatingPosting> togetherPostings = (List<TogetherEatingPosting>)(Object)postings;
			mav.addObject("postings", togetherPostings);
		}	
			
		mav.setViewName("/posting/post_list");
		return mav;
		
		
	}

	

	
	
	
	
}
