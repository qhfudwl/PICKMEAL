package pickmeal.dream.pj.restaurant.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.member.service.MemberService;
import pickmeal.dream.pj.restaurant.domain.FavoriteRestaurant;
import pickmeal.dream.pj.restaurant.domain.Restaurant;
import pickmeal.dream.pj.restaurant.service.FavoriteRestaurantSerivce;

@Controller
public class FavoriteRestaurantController {
	
	@Autowired
	FavoriteRestaurantSerivce frs;
	
	@Autowired
	MemberService ms;
	
	@GetMapping("/findAllFavorateRestaurant")
	public ModelAndView findAllFavoriteRestaurant(HttpSession session) {
		List<FavoriteRestaurant> frlist = new ArrayList<FavoriteRestaurant>();
		
		Member member = (Member) session.getAttribute("member");
		
		frlist = frs.findFavoriteRestaurantBymemberId(member.getId());
		for( FavoriteRestaurant fr : frlist) {
			Restaurant restaurant = frs.findRestaurantById(fr.getRestaurant().getId());
			fr.setRestaurant(restaurant);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("frlist",frlist);
		mav.addObject("member",member);
		mav.setViewName("찜식당 리스트 화면이욤");
		
		return mav;
		
	}
	
	
}
