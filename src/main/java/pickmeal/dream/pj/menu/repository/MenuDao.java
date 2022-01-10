package pickmeal.dream.pj.menu.repository;

import java.util.List;

import pickmeal.dream.pj.menu.domain.Menu;
import pickmeal.dream.pj.menu.domain.Menuclassify;

public interface MenuDao {
	
	void addMenu(Menu menu);
	
	List<Menu> findAllMenus();
	
	List<Menu> findMenuByClassify(Menuclassify menuclassify);
	
	List<Menu> findMenuBywheater(int weather);
	
	Menu findMenuById(long id);
	
	List<Menu> findMenuByMenuName(String menuName);
}
