package pickmeal.dream.pj.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.java.Log;
import pickmeal.dream.pj.member.command.MemberCommand;
import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.member.service.MemberService;

@Controller
@Log
public class SignUpController {
	
	@Autowired
	MemberService ms;
	
	@GetMapping("/member/viewSignUp")
	public ModelAndView viewSignUp() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("memberCommand", new MemberCommand());
		mav.setViewName("member/sign_up");
		return mav; 
	}
	
	@GetMapping(value="/member/checkSignUpInfo", produces="application/json; charset=utf8")
	@ResponseBody
	public String checkSignUpInfo(@RequestParam("signInfo") String signInfo, 
			@RequestParam("type") String type) {
		String check = null;
		
		if (signInfo.equals("")) {
			check = "";
		} else {
			if (type.equals("email")) {
				if(!ms.isMemberByEmail(signInfo)) {
					check = "ok";
				} else {
					check = "중복 이메일입니다.";
				}
			} else if (type.equals("nickName")) {
				if (!ms.isMemberByNickName(signInfo)) {
					check = "ok";
				} else {
					check = "중복 닉네임입니다.";
				}
			}
		}
		
		
		return check;
	}
	
	/**
	 * 회원 가입 후 메인 화면으로 보내기
	 * @param mc
	 * @param session
	 * @return
	 */
	@PostMapping("/member/generateMember")
	public String generateMember(@ModelAttribute MemberCommand mc, 
			HttpSession session) {
		
		// command 를 domain 객체로 만든다.
		Member member = new Member();
		member.setEmail(mc.getEmail());
		member.setPasswd(mc.getPasswd());
		member.setNickName(mc.getNickName());
		member.setBirth(mc.getBirth());
		member.setGender(mc.getGender());
		
		// 셋팅한 객체를 서비스로 보낸다.
		member = ms.addMember(member);

		// session에 들어갈 member
		Member enterMember = new Member();
		enterMember.setId(member.getId());
		enterMember.setEmail(member.getEmail());
		enterMember.setNickName(member.getNickName());
		
		log.info(member.toString());
		
		
		// 완료 후 세션에 멤버를 넣어준다 (자동 로그인)
		session.setAttribute("member", enterMember);
		
		// 요청 정보를 버리고 리다이렉션으로 메인화면으로 보낸다.
		return "redirect:/viewIndexMap";
	}
}
