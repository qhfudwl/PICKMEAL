package pickmeal.dream.pj.menu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import pickmeal.dream.pj.config.DataSourceConfig;
import pickmeal.dream.pj.menu.domain.Menu;
import pickmeal.dream.pj.menu.domain.Menuclassify;
@Service
public class MenuServiceTest {
	private static MenuService ms;
	@Autowired
	public MenuServiceTest(MenuService menuService) {
		this.ms = menuService;
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DataSourceConfig.class);
		MenuServiceTest test = context.getBean("menuServiceTest",MenuServiceTest.class);
		
		Menuclassify menuclassify = new Menuclassify();
		menuclassify.setSoupy(2);
		menuclassify.setHot_ice(1);
		menuclassify.setCarbohydrate(0);
		menuclassify.setMainFood(2);
		menuclassify.setSpicy(1);
		System.out.println(menuclassify);
		System.out.println(ms.findMenuByClassify(menuclassify));
		
	}
	public List<Menu> AllMenu(){
		System.out.println(ms.findAllMenus());
		return ms.findAllMenus();
	}
}
