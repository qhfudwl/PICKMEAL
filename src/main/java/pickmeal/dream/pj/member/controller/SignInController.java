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
import pickmeal.dream.pj.member.command.MemberCommand;
import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.member.service.MemberService;

@Controller
@Log
public class SignInController {
	
	@Autowired
	MemberService ms;
	
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
			if (memberCommand.getPasswd() != member.getPasswd()) { // 비밀번호 불일치 시
				chkInfo = false;
			}
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

		// 업데이트 후 session 에 담아서 메인 화면으로 보낸다.
		session.setAttribute("member", enterMember);
		mav.setViewName("redirect:/viewIndexMap");
		return mav;
	}
}
