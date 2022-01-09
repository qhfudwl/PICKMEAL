package pickmeal.dream.pj.game.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.java.Log;
import pickmeal.dream.pj.web.controller.MapController_SJW;

@Controller
@Log
public class gameController {
	
//	@GetMapping("/gamePlayForm")
//	public String gamePlayForm() {
//		return "gamePlay_SJW";
//	}
//	
	
	@GetMapping("/openGamePopUp")
	public String openGamePopUp() {
		return "gamePlay_SJW";
	}
	
	@GetMapping("/sendDataToPopUp")
	public ModelAndView sendDataToPopUp(@RequestParam("radius")String radius, @RequestParam("category")String category) {
		
		ModelAndView mav = new ModelAndView();
		System.out.println("test");
		System.out.println("radius : " + radius + " / category : " + category);
		
		mav.addObject("radius", radius);
		mav.addObject("category", category);
		
		mav.setViewName("gamePlay_SJW");
		
		return mav;
	}
	
	
	
	
	
	
//	왜 모델앤뷰로 안되냐고!!!!!!!!!!!
	
//	@GetMapping("/openGamePopUp")
//	public String openGamePopUp(HttpServletRequest request, Model model) {
//		System.out.println("test");
//		
//		String radius = request.getParameter("radius");
//		String category = request.getParameter("category");
//	
//		
//		model.addAttribute("radius", radius);
//		model.addAttribute("category", category);
//		
//		return "gamePlay_SJW";
//	}
	
}
