package pickmeal.dream.pj.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.java.Log;
import pickmeal.dream.pj.coupon.domain.Coupon;
import pickmeal.dream.pj.coupon.domain.CouponCategory;
import pickmeal.dream.pj.coupon.service.CouponService;
import pickmeal.dream.pj.member.command.MemberCommand;
import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.member.service.MemberService;
import pickmeal.dream.pj.member.util.PasswordDecoding;
import pickmeal.dream.pj.restaurant.domain.Restaurant;

@Controller
@Log
public class SignInController {
	
	@Autowired
	MemberService ms;
	/*쿠폰 서비스 추가*/
	@Autowired
	CouponService cs;
	
	@Autowired
	private PasswordDecoding pd;
	
	@GetMapping("/member/viewSignIn")
	public ModelAndView viewSignIn() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("memberCommand", new MemberCommand());
		mav.setViewName("member/sign_in");
		return mav;
	}
	
	@PostMapping("/member/signInMember")
	public ModelAndView signInMember(@ModelAttribute MemberCommand memberCommand
			, @RequestParam("chkBtn") String chkBtn, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		Member member = null;
		boolean chkInfo = true;
		if (chkBtn.equals("회원가입")) { // 회원가입의 경우 바로 회원가입 폼으로 가야한다.
			mav.setViewName("redirect:/member/viewSignUp");
			return mav;
		}
		if (!ms.isMemberByEmail(memberCommand.getEmail())) { // 해당 아이디가 없을 경우
			chkInfo = false;
		} else { // 해당 아이디가 있을 경우
			member = ms.findMemberByMemberEmail(memberCommand.getEmail());
			member = pd.convertPassword(member);
			log.info(member.getPasswd());
			if (!memberCommand.getPasswd().equals(member.getPasswd())) { // 비밀번호 불일치 시
				chkInfo = false;
			}
		}
		if (member.getMemberType() == 'D') { // 탈퇴한 회원
			chkInfo = false;
		}
		if (!chkInfo) { // 유효성 검사에서 걸릴 경우 다시 로그인 화면으로 보낸다
			mav.addObject("memberCommand", memberCommand);
			mav.addObject("invalidInfo", "true");
			mav.setViewName("/member/sign_in");
			return mav;
		}
		// 유효성 검사를 마친 후 사용자 정보 업데이트 필요
		member = ms.signInMember(member);
		
		
		// 필요한 정보만 setting
		Member enterMember = new Member();
		enterMember.setId(member.getId());
		enterMember.setEmail(member.getEmail());
		enterMember.setNickName(member.getNickName());
		enterMember.setAttendance(member.getAttendance());
		enterMember.setFoodPowerPoint(member.getFoodPowerPoint());
		enterMember.setMannerTemperature(member.getMannerTemperature());
		enterMember.setProfileImgPath(member.getProfileImgPath());
		
		log.info(enterMember.toString());

		// 업데이트 후 session 에 담아서 메인 화면으로 보낸다.
		session.setAttribute("member", enterMember);
		
		/*쿠폰 서비스 추가*/	
		if(!(session.getAttribute("member") == null) && !(session.getAttribute("restaurant") == null) && !(session.getAttribute("couponCategory") == null)) {
		Member member2 = (Member) session.getAttribute("member");
		Restaurant restaurant = (Restaurant) session.getAttribute("restaurant");
		CouponCategory couponCategory = (CouponCategory) session.getAttribute("couponCategory");
		if(cs.findCouponByMemberIdinToday(member.getId())==1) {
			if(cs.findCouponBymemberIdinTodayMax(member.getId())<=2) {
				Coupon coupon = new Coupon();
				coupon.setMember(member2);
				coupon.setRestaurant(restaurant);
				coupon.setCouponCategory(couponCategory);
				cs.addCoupon(coupon);
				}
			}else {
				Coupon coupon = new Coupon();
				coupon.setMember(member2);
				coupon.setRestaurant(restaurant);
				coupon.setCouponCategory(couponCategory);
				cs.addCoupon(coupon);
			}
		session.removeAttribute("couponCategory");
				}
		mav.setViewName("redirect:/index");
		log.info("들어오는지 확인");
		return mav;
	}
}
