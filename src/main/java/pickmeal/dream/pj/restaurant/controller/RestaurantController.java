package pickmeal.dream.pj.restaurant.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pickmeal.dream.pj.message.service.MessageService;
import pickmeal.dream.pj.restaurant.domain.RestaurantReference;
import pickmeal.dream.pj.restaurant.domain.Review;
import pickmeal.dream.pj.restaurant.service.RestaurantReferenceService;
import pickmeal.dream.pj.restaurant.service.ReviewService;

@Controller
public class RestaurantController {
	@Autowired
	RestaurantReferenceService rrs;
	
	@Autowired
	ReviewService rs;
	
	@Autowired
	MessageService ms;
	
	/*
	 *  메인화면 불러오기
	 * 
	 */
	@GetMapping("/index")
	public ModelAndView index() {
		
		
		//레스토랑 아이디 셋팅
		long restaurantId =1;
		
		//포춘쿠키 메세지 셋팅
		
		String fortuneMessage = ms.getMessageByType('F');
		
		ModelAndView mav = new ModelAndView();
		//레스토랑 아이디 추가
		mav.addObject("restaurantId",restaurantId);
		//포춘메세지 추가
		mav.addObject("fortuneMessage",fortuneMessage);
		//View 이름 설정
		mav.setViewName("index");
		return mav;
		
	}
	
	
	
	/**
	 * 식당 리뷰 관련 정보 
	 * 		- 식당 성별/연령별 정보
	 * 		- 식당 리뷰들 정보
	 * 
	 * @param restaurantId
	 * @return
	 * @author 윤효심
	 */
	@PostMapping("restaurant_sub_info")
	@ResponseBody
	public HashMap<String, Object> getRestaurantSubInfo(@RequestParam long restaurantId){

		RestaurantReference restReference = rrs.getRestaurantReference(restaurantId);
		Review review = rs.getReview(restaurantId);
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		result.put("restReference", restReference);
		result.put("review", review);
		
		return result;
		
	}
	
	
	
	@GetMapping("usePointPopup")
	public String testUserPoint() {
		return "/index2";
	}
	
}
