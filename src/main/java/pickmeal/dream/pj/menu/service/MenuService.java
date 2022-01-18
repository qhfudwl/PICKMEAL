package pickmeal.dream.pj.menu.service;

import java.util.List;

import pickmeal.dream.pj.menu.domain.Menu;
import pickmeal.dream.pj.menu.domain.Menuclassify;
import pickmeal.dream.pj.weather.command.WeatherCommand;

public interface MenuService {
	
	void addMenu(Menu Menu);
	
	List<Menu> findAllMenus();
	
	Menu findMenuByClassify(Menuclassify menuclassify);
	
	Menu findMenuByWeather(WeatherCommand wc);
}
