package pickmeal.dream.pj.menu.service;

import java.util.List;

import pickmeal.dream.pj.menu.domain.Menu;
import pickmeal.dream.pj.menu.domain.Menuclassify;

public interface MenuService {
	
	void addMenu(Menu Menu);
	
	List<Menu> findAllMenus();
	
	List<Menu> findMenuByClassify(Menuclassify menuclassify);
	
	List<Menu> findMenuBywheather(int weather);
}
