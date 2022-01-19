package pickmeal.dream.pj.menu.repository;

import java.util.List;

import pickmeal.dream.pj.menu.domain.Menu;
import pickmeal.dream.pj.menu.domain.Menuclassify;
import pickmeal.dream.pj.weather.domain.Weather;

public interface MenuDao {
	
	void addMenu(Menu menu);
	
	List<Menu> findAllMenus();
	
	List<Menu> findMenuByClassify(Menuclassify menuclassify);
	
	Menu findMenuById(long id);
	
	List<Menu> findMenuByMenuName(String menuName);
	
	List<Menu> findMenuByWeather(int temperature, int sky);
}
