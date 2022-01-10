package dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.java.Log;
import pickmeal.dream.pj.config.DataSourceConfig;
import pickmeal.dream.pj.menu.domain.Menu;
import pickmeal.dream.pj.menu.domain.Menuclassify;
import pickmeal.dream.pj.menu.repository.MenuDao;

@SpringJUnitConfig(classes= {DataSourceConfig.class})
@Log
public class MenuDaoTest {

	@Autowired
	private MenuDao md;
	
	@Test
	@Transactional
	public void findMenuByClassify() {
		Menuclassify menuclassify = new Menuclassify();
		menuclassify.setSoupy(1);
		menuclassify.setHot_ice(0);
		menuclassify.setCarbohydrate(2);
		menuclassify.setMainFood(1);
		menuclassify.setSpicy(0);
		List<Menu> menulist = new ArrayList<Menu>();
		menulist = md.findMenuByClassify(menuclassify);
		//console.log(menulist);
		log.info("성공?");
	}
}
