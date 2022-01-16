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
	
	/**
	 * @author 김보령
	 */
	static final double EARTH = 6371000; // 단위 m
	static final double RADIUS = (Math.PI / 180);
	
	
	/*
	 *  메인화면 불러오기
	 * 
	 */
	@GetMapping("/index")
	public ModelAndView index() {
		
		
		//레스토랑 아이디 셋팅(임시)- 윤효심
		long restaurantId =1;
		
		//포춘쿠키 메세지 셋팅 - 윤효심
		String fortuneMessage = ms.getMessageByType('F');
		
		ModelAndView mav = new ModelAndView();
		//레스토랑 아이디 추가 - 윤효심 
		mav.addObject("restaurantId",restaurantId);
		//포춘메세지 추가 - 윤효심 
		mav.addObject("fortuneMessage",fortuneMessage);
		//View 이름 설정
		mav.setViewName("index");
		return mav;
		
	}
	
	
	
	/**
	 * 식당 리뷰 관련 정보 
	 * 		- 식당 성별/연령별 정보
	 * 
	 * @param restaurantId
	 * @return
	 * @author 윤효심
	 */
	@PostMapping("restaurant_sub_info_reference")
	@ResponseBody
	public HashMap<String, Object> getRestaurantSubInfoForReference(@RequestParam long restaurantId){
		//레스토랑 정보가 있으면 
		RestaurantReference restReference = null;
		
		
		restReference = rrs.getRestaurantReference(restaurantId);

		HashMap<String, Object> result = new HashMap<String, Object>();
		
		//레스토랑 정보가 없으면
		result.put("restReference", restReference);
		
		
		return result;
		
	}
	
	/**
	 * 식당 리뷰 관련 정보 
	 * 		- 식당 리뷰들 정보
	 * 
	 * @param restaurantId
	 * @return
	 * @author 윤효심
	 */
	@PostMapping("restaurant_sub_info_review")
	@ResponseBody
	public HashMap<String, Object> getRestaurantSubInfoForReview(@RequestParam long restaurantId){
		//레스토랑 정보가 있으면 
		
		Review review = null;
		
		
		review = rs.getReview(restaurantId);
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		//레스토랑 정보가 없으면
	
		result.put("review", review);
		
		return result;
		
	}
	
	
	/**
	 * 
	 * @param nowLat
	 * @param nowLng
	 * @param diffM
	 * @return
	 * @author 김보령
	 */
	@GetMapping("/calculateMap")
	@ResponseBody
	public ResponseEntity<?> calculateMap(@RequestParam("nowLat") double nowLat, 
			@RequestParam("nowLng") double nowLng, @RequestParam("diffM") double diffM) {
		

		double diffLat = LatitudeInDifference(diffM);
		double diffLng = LongitudeInDifference(nowLat, diffM);
		
		double lat1 = nowLat - diffLat;
		double lng1 = nowLng - diffLng;
		double lat2 = nowLat + diffLat;
		double lng2 = nowLng + diffLng;
		
		double[] latLng = {lat1, lng1, lat2, lng2};
		
		return ResponseEntity.ok(latLng);
	}
	
	/**
	 * 
	 * @param diff
	 * @return
	 * @author 김보령 
	 */
    //반경 m이내의 위도차(degree)
    public double LatitudeInDifference(double diff){
 
        return (diff*360.0) / (2*Math.PI*EARTH);
    }
 
    /**
     * 
     * @param _latitude
     * @param diff
     * @return
     * @author 김보
     */
    //반경 m이내의 경도차(degree)
    public double LongitudeInDifference(double _latitude, double diff){
 
        return (diff*360.0) / (2*Math.PI*EARTH*Math.cos(Math.toRadians(_latitude)));
    }
	

	
}
