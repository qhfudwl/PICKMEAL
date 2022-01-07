package pickmeal.dream.pj.menu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

import pickmeal.dream.pj.menu.dao.MenuDao;
import pickmeal.dream.pj.menu.domain.Menu;
import pickmeal.dream.pj.menu.domain.Menuclassify;

@Component("menuService")
public class MenuServiceIpml implements MenuService{
	MenuDao md;
	@Override
	public void addMenu(Menu Menu) {
		
	}

	@Override
	public List<Menu> findAllMenus() {
		List<Menu> menulist = new ArrayList<Menu>();
		menulist = md.findAllMenus();
		
		return menulist;
	}

	@Override
	public Menu findMenuByClassify(Menuclassify menuclassify) {
		List<Menu> menulist = new ArrayList<Menu>();
		
		if(menuclassify.getSoupy() == 2) {
			Random random = new Random();
			int randomMenuIndex = random.nextInt(2);
			menuclassify.setSoupy(randomMenuIndex);
		}
		if(menuclassify.getHot_ice() == 2) {
			Random random = new Random();
			int randomMenuIndex = random.nextInt(2);
			menuclassify.setHot_ice(randomMenuIndex);
		}
		if(menuclassify.getCarbohydrate() == 4) {
			Random random = new Random();
			int randomMenuIndex = random.nextInt(4);
			menuclassify.setCarbohydrate(randomMenuIndex);
		}
		if(menuclassify.getMainFood() == 3) {
			Random random = new Random();
			int randomMenuIndex = random.nextInt(3);
			menuclassify.setMainFood(randomMenuIndex);
		}
		if(menuclassify.getSpicy() == 2) {
			Random random = new Random();
			int randomMenuIndex = random.nextInt(2);
			menuclassify.setSpicy(randomMenuIndex);
		}
		
		menulist = md.findMenuByClassify(menuclassify);
		
		Random random = new Random();
		int randomMenuIndex = random.nextInt(menulist.size());
		
		Menu randomMenu = menulist.get(randomMenuIndex);
		
		
		return randomMenu;
	}

	@Override
	public Menu findMenuBywheather(int weather) {
		return null;
	}

}
