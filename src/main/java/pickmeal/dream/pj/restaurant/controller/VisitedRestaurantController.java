package pickmeal.dream.pj.restaurant.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pickmeal.dream.pj.member.command.MemberCommand;
import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.member.service.MemberService;
import pickmeal.dream.pj.restaurant.command.ReviewCommand;
import pickmeal.dream.pj.restaurant.domain.FavoriteRestaurant;
import pickmeal.dream.pj.restaurant.domain.Restaurant;
import pickmeal.dream.pj.restaurant.domain.Review;
import pickmeal.dream.pj.restaurant.domain.ReviewItem;
import pickmeal.dream.pj.restaurant.domain.VisitedRestaurant;
import pickmeal.dream.pj.restaurant.repository.VisitedRestaurantDao;
import pickmeal.dream.pj.restaurant.service.FavoriteRestaurantSerivce;
import pickmeal.dream.pj.restaurant.service.ReviewService;
import pickmeal.dream.pj.restaurant.service.VisitedRestaurantService;
import pickmeal.dream.pj.web.util.Validator;

@Controller
public class VisitedRestaurantController {
	
	@Autowired
	VisitedRestaurantService vrs;
	
	@Autowired
	MemberService ms;
	
	@Autowired
	FavoriteRestaurantSerivce frs;
	
	@Autowired
	Validator v;
	
	@Autowired
	VisitedRestaurantDao vrd;
	
	@Autowired
	ReviewService reviewService;
	
	
	/**
	 * 회원이 내가 간 식당의 리스트를 받아 올 경우 사용
	 * @param session
	 * @return
	 */
	@GetMapping("/findAllVisitedRestaurant")
	public ModelAndView findAllVisitedRestaurant(HttpSession session) {
		List<VisitedRestaurant> vrlist = new ArrayList<VisitedRestaurant>();
		Member member = (Member) session.getAttribute("member");
		/*멤버가 없으면 로그인 화면으로 이동*/
		if(member == null) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("memberCommand", new MemberCommand());
			mav.setViewName("member/sign_in");
			return mav;
		}
		/*멤버가 있으면 동작*/
		else {
		List<Boolean> flist = new ArrayList<Boolean>();	
		vrlist = vrs.findAllVisitedRestaurantByMemberId(member.getId());
		for(VisitedRestaurant v : vrlist) {
			Restaurant r = frs.findRestaurantById(v.getRestaurant().getId());
			boolean f = frs.isFavoriteRestaurant(member.getId(), v.getRestaurant().getId());
			v.setRestaurant(r);
			
			flist.add(f);
			
		}
		ModelAndView mav = new ModelAndView();
			mav.addObject("vrlist",vrlist);
			mav.addObject("flist",flist);
			mav.setViewName("restaurant/visited_restaurant_list");
			return mav;
		}
	}
	
	/**
	 * 내가 간 식당에서 버튼을 눌러 찜식당으로 바로 넣어주기
	 * @param map
	 * @return
	 */
	/*내가 간 식당에서 찜식당 바로 추가*/
	@PostMapping("/jjimclickBtn")
	@ResponseBody
	public ResponseEntity<?> jjimclickBtn(@RequestBody Map<String,String> map){
		
		String id = map.get("id");
		long longid = Long.valueOf(id);
		VisitedRestaurant v = vrs.findVisitedRestaurantById(longid);
		FavoriteRestaurant f = new FavoriteRestaurant();
		f.setMember(v.getMember());
		f.setRestaurant(v.getRestaurant());
		frs.addFavoriteRestaurant(f);
		return ResponseEntity.ok(true);
	}
	
	
	/**
	 * 메인 화면에서 뜬 식당정보를 버튼을 눌러 찜식당으로 추가 해주기
	 * @param map
	 * @return
	 */
	/*메인화면에서 찜식당 바로 추가*/
	@PostMapping("/indexFavorite")
	@ResponseBody
	public ResponseEntity<?> indexFavorite(@RequestBody Map<String,String> map){
		
		String restaurantId = map.get("restaurantId");
		long longRid = Long.valueOf(restaurantId);
		Restaurant r = frs.findRestaurantById(longRid);
		
		String memberId =map.get("memberId");
		long longMid = Long.valueOf(memberId);
		Member m = ms.findMemberById(longMid);
		
		FavoriteRestaurant f = new FavoriteRestaurant();
		f.setMember(m);
		f.setRestaurant(r);
		frs.addFavoriteRestaurant(f);
		
		if(frs.isFavoriteRestaurant(longMid, longRid)) {
			return ResponseEntity.ok(true);
		}
		return ResponseEntity.ok(null); 
	}
	
	@PostMapping("/removeVisited")
	@ResponseBody
	public ResponseEntity<?> removeVisited(@RequestBody Map<String,String> map){
		String vrId = map.get("id");
		long longvrId = Long.valueOf(vrId);
		System.out.println(longvrId);
		vrs.removeVisitiedRestaurantById(longvrId);
		if(vrd.isVisitedRestaurantById(longvrId)) {
			System.out.println("테이블에서 안지워짐");
			return ResponseEntity.ok(false);
		}else {
			System.out.println("테이블에서 지워짐");
			return ResponseEntity.ok(false);
		}
	}
	
	
	/**
	 * 리뷰하기
	 * @param rc
	 * @param session
	 * @param visitedRestaurantId
	 * @return
	 */
	@PostMapping("/reviewSeccess")
	public ModelAndView writeReview(ReviewCommand rc, HttpSession session, @RequestParam("visitedRestaurantId") long visitedRestaurantId ) {
		List<VisitedRestaurant> vrlist = new ArrayList<VisitedRestaurant>();
		Member member = (Member) session.getAttribute("member");
		reviewService.setReview(rc);
		vrd.writeVisitedRestaurantReviewById(visitedRestaurantId);
		/*멤버가 없으면 로그인 화면으로 이동*/
		if(member == null) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("memberCommand", new MemberCommand());
			mav.setViewName("member/sign_in");
			return mav;
		}
		/*멤버가 있으면 동작*/
		else {
		List<Boolean> flist = new ArrayList<Boolean>();	
		vrlist = vrs.findAllVisitedRestaurantByMemberId(member.getId());
		for(VisitedRestaurant v : vrlist) {
			Restaurant restaurant = frs.findRestaurantById(v.getRestaurant().getId());
			boolean f = frs.isFavoriteRestaurant(member.getId(), v.getRestaurant().getId());
			v.setRestaurant(restaurant);
			
			flist.add(f);
			
		}
		ModelAndView mav = new ModelAndView();
			mav.addObject("vrlist",vrlist);
			mav.addObject("flist",flist);
			mav.setViewName("restaurant/visited_restaurant_list");
			return mav;
		}
		
	}	
		
		
		

	/*Review re = new Review();
	List<ReviewItem> itemlist = new ArrayList<ReviewItem>();
	int i = re.getReviewItem().get(0).getReviewCount();
	int j = i + r.getBathroom();
	itemlist = re.getReviewItem();
	itemlist.get(0).getReviewCount();*/
	
}
