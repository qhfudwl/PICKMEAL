package pickmeal.dream.pj.coupon.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.CDATASection;

import com.google.protobuf.Empty;

import pickmeal.dream.pj.coupon.domain.Coupon;
import pickmeal.dream.pj.coupon.domain.CouponCategory;
import pickmeal.dream.pj.coupon.repository.CouponDao;
import pickmeal.dream.pj.coupon.service.CouponService;
import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.member.service.MemberService;
import pickmeal.dream.pj.restaurant.domain.Restaurant;
import pickmeal.dream.pj.restaurant.service.FavoriteRestaurantSerivce;

@Controller
public class CouponCountroller {
	
	@Resource(name="couponService")
	CouponService cs;
	@Resource(name="memberService")
	MemberService ms;
	@Autowired
	FavoriteRestaurantSerivce frs;
	@Autowired
	CouponDao cd;
	/**
	 * 쿠폰발생 시점(게임하고 식당 띄워줄 때) 이거 안써용 
	 * @return
	 */
	@GetMapping("/couponGenerate")
	public ModelAndView CouponGenerate() {
		
		ModelAndView mav = new ModelAndView();
		//mav.addObject("coupon",coupon);
		mav.setViewName("menu/coupongeneratetest");
		
		return mav;
		
	}
	/**
	 * 식당게임이 끝난 경우 식당 추천해주는 버튼에 추가 해주기!
	 * @param session
	 * @return
	 */
	@GetMapping("/startPage")
	public ModelAndView StartPage(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		session.removeAttribute("couponCategory");
		mav.setViewName("coupon/restaurant_game_example");
		return mav;
	}
	/**
	 * 게임 끝난 후 식당 정보를 보고 식당이 제휴식당인지 확인 후, 
	 * 쿠폰 카테고리 발행 성공 // 실패 성공시 쿠폰 카테고리를 세션에 저장
	 * **이거 지금 테스트용 Service부르는거라 변경 필요함!!
	 * CouponCategory couponCategory = cs.findCouponCategoryTest(); 이 부분
	 * 이거 통과 하게되면 메인화면에 쿠폰 받으러 가는 버튼 생성해준다.
	 * @return
	 */
	
	@GetMapping("/couponCategoryGeneric")
	public ModelAndView CouponCategoryGeneric(HttpSession session) {
		/*이거 나중에 게임 완료 후로 다 옴겨야해용 다 옴겨야행용*/
		/*레스토랑이 제휴 레스토랑인지 비교 하기*/
		System.out.println("이화면 입장 완료여");
		Restaurant restaurant = cs.findRestaurantById(9);
		System.out.println(restaurant);
		System.out.println(restaurant.isRType());

		Member member = (Member) session.getAttribute("member");
		/*레스토랑이 제휴 레스토랑인지 비교 하기*/
		if(restaurant.isRType()== true) {
			
			/*멤버라면?*/
			if(member !=null) {
				System.out.println("에러 여기서나나?");
				/*오늘 받은 쿠폰개수 확인 해주기*/
				if(cs.findCouponByMemberIdinToday(member.getId())==1) {
					if(cs.findCouponBymemberIdinTodayMax(member.getId())<=2) {
						
						/*제휴 레스토랑이면 메소드 돌려서 쿠폰나오면 발급 해주기*/
						CouponCategory couponCategory = cs.findCouponCategoryGo();
						System.out.println("트루값 받고 여기 들어옴");
						
						/*쿠폰이 발급이 안된 경우 리턴값이 없을 경우 그냥 통과*/
						if(couponCategory == null) {
						System.out.println("없는쪽임 = " + couponCategory);
						}
						/*쿠폰이 발급 된 경우*/
						else{
						/*쿠폰이 발급이 되어 리턴값이 있을 경우는 세션에 저장 레스토랑, 쿠폰카테고리.*/
						session.setAttribute("couponCategory", couponCategory);
						session.setAttribute("restaurant", restaurant);	
						//System.out.println(session.getAttribute("member"));
						//System.out.println("카테고리,식당 세션등록 완료");	
						}
					}
					/*오늘받은 쿠폰이 3개인 경우 그냥 통과*/
					else {
						System.out.println("난 오늘 3개 받았어");
					}
					/*오늘 발급 받은적 없으면*/
				}else {
					/*제휴 레스토랑이면 메소드 돌려서 쿠폰나오면 발급 해주기*/
					CouponCategory couponCategory = cs.findCouponCategoryGo();
					System.out.println("트루값 받고 여기 들어옴");
					
					/*쿠폰이 발급이 안된 경우 리턴값이 없을 경우 그냥 통과*/
					if(couponCategory == null) {
					System.out.println("없는쪽임 = " + couponCategory);
					}
					/*쿠폰이 발급 된 경우*/
					else{
					/*쿠폰이 발급이 되어 리턴값이 있을 경우는 세션에 저장 레스토랑, 쿠폰카테고리.*/
					session.setAttribute("couponCategory", couponCategory);
					session.setAttribute("restaurant", restaurant);	
					//System.out.println(session.getAttribute("member"));
					//System.out.println("카테고리,식당 세션등록 완료");	
					}
				}
			}
			/*멤버가 아니라면*/
			else {
				CouponCategory couponCategory = cs.findCouponCategoryGo();
				/*쿠폰이 발급이 안되서 리턴값이 없을 경우 그냥 통과*/
				System.out.println("트루값 받고 여기 들어옴");
				if(couponCategory == null) {
				System.out.println("왜? 없는쪽임 = " + couponCategory);
				}else {

				session.setAttribute("couponCategory", couponCategory);
				session.setAttribute("restaurant", restaurant);
				System.out.println("멤버가없다용");
				}
			}
		}
		/*제휴 식당이 아니라면*/
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("coupon/index_couponCategory_generate");
		return mav;
	}
		
	/**
	 * 쿠폰 버튼을 눌러 팝업창으로 이동시에 회원과 쿠폰카테고리를 들고 입장한다.
	 * 멤버가 없을경우 로그인 / 취소버튼으로 있을경우는 쿠폰발급 버튼이 나온다. 
	 * @param session
	 * @return
	 */
	@GetMapping("/couponPopupCreate")
	public ModelAndView CouponPopupCreate(HttpSession session) {
		CouponCategory couponCategory = new CouponCategory();
		Member member = new Member();
		couponCategory = (CouponCategory) session.getAttribute("couponCategoty");
		member = (Member) session.getAttribute("member");
		
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("coupon/coupon_generate_popup");
		
		return mav;
	}
	
	
	@PostMapping("/genericJincoupon")
	@ResponseBody
	public ResponseEntity<?> genericJinCoupon(HttpSession session){
		boolean b;
		
		Member member = (Member)session.getAttribute("member");
		Restaurant restaurant = (Restaurant)session.getAttribute("restaurant");
		CouponCategory couponCategory = (CouponCategory)session.getAttribute("couponCategory");
		
		Coupon coupon = new Coupon();
		coupon.setMember(member);
		coupon.setRestaurant(restaurant);
		coupon.setCouponCategory(couponCategory);
		cs.addCoupon(coupon);
		System.out.println("쿠폰은 어떤가요~!"+coupon);
		if(cd.isCouponByCouponNumber(coupon.getCouponNumber()))
			b = true;
		else
			b = false;
		System.out.println(b+" 리턴 할게요!");
		return ResponseEntity.ok(b);
	}
	
	
	
}
