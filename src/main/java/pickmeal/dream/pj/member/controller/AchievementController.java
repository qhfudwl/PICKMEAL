package pickmeal.dream.pj.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AchievementController {
	
	@GetMapping("/member/updateAchievementInfo")
	@ResponseBody
	public void updateAchievementInfo() {
		
	}
}
