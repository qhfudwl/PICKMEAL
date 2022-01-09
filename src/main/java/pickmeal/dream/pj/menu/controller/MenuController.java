package pickmeal.dream.pj.menu.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import pickmeal.dream.pj.menu.domain.Menu;
import pickmeal.dream.pj.menu.domain.Menuclassify;
import pickmeal.dream.pj.menu.service.MenuService;
import pickmeal.dream.pj.web.command.MenuClassifyCommand;


@Controller
public class MenuController {
	@Resource(name="menuService")
	MenuService ms;
	/**
	 * 게임화면으로 넘어가기!!
	 * @return
	 */
	@GetMapping("/menuGamePopup")
	public ModelAndView menuGamePopup() {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("menu/menu_game_popup");
		return mav;
		
	}
	@GetMapping("/menuGameSuccess")
	public ModelAndView menuGameSuccess(@ModelAttribute MenuClassifyCommand msCommand) {
		System.out.println(msCommand);
		Menuclassify menuclassify = new Menuclassify(msCommand.getSoupy(),msCommand.getHot_ice()
				,msCommand.getCarbohydrate(),msCommand.getMainFood(),msCommand.getSpicy());
		Menu menu = ms.findMenuByClassify(menuclassify);
		System.out.println(menu);
		ModelAndView mav = new ModelAndView();
		mav.addObject("menu", menu);
		mav.setViewName("menu/menu_game_success");
		return mav;
	}
}
