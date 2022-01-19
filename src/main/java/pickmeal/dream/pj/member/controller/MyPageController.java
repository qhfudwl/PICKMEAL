package pickmeal.dream.pj.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import pickmeal.dream.pj.member.domain.Member;

@Controller
public class MyPageController {
	
	@GetMapping("/member/myPage")
	public ModelAndView myPage(@SessionAttribute("member") Member member) {
		ModelAndView mav = new ModelAndView();
		System.out.println(member);
		mav.setViewName("member/my_page");
		return mav;
	}
}
