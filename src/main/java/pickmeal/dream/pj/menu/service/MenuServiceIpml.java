package pickmeal.dream.pj.menu.service;

import java.util.List;

import org.springframework.stereotype.Component;

import pickmeal.dream.pj.menu.domain.Menu;
import pickmeal.dream.pj.menu.domain.Menuclassify;

@Component("menuService")
public class MenuServiceIpml implements MenuService{

	@Override
	public void addMenu(Menu Menu) {
		
	}

	@Override
	public List<Menu> findAllMenus() {
		return null;
	}

	@Override
	public List<Menu> findMenuByClassify(Menuclassify menuclassify) {
		return null;
	}

	@Override
	public List<Menu> findMenuBywheather(int weather) {
		return null;
	}

}
