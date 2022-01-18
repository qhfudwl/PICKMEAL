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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pickmeal.dream.pj.member.command.MemberCommand;
import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.member.service.MemberService;
import pickmeal.dream.pj.restaurant.domain.FavoriteRestaurant;
import pickmeal.dream.pj.restaurant.domain.Restaurant;
import pickmeal.dream.pj.restaurant.domain.VisitedRestaurant;
import pickmeal.dream.pj.restaurant.service.FavoriteRestaurantSerivce;
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
}
