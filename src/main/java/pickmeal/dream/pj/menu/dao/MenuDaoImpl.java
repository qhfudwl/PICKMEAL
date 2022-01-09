package pickmeal.dream.pj.menu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pickmeal.dream.pj.menu.domain.Menu;
import pickmeal.dream.pj.menu.domain.Menuclassify;

@Repository("menuDao")
public class MenuDaoImpl implements MenuDao{
	
	//@Resource(name="jdbcTemplate")
	//private JdbcTemplate jt;
	@Autowired
	JdbcTemplate jt;
	
	@Override
	public void addMenu(Menu menu) {
		String sql = "INSERT INTO Menu(menuName, weather, imgPath, soupy, hot_ice, carbohydrate, mainFood, spicy)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		jt.update(sql, menu.getMenuName(),menu.getWeather(), menu.getImgPath(),
				menu.getMenuclassify().getSoupy(), menu.getMenuclassify().getHot_ice(), menu.getMenuclassify().getCarbohydrate(),
				menu.getMenuclassify().getMainFood(), menu.getMenuclassify().getSpicy());
		
	}

	@Override
	public List<Menu> findAllMenus() {
		String sql = "SELECT id, menuName, weather, imgPath, soupy, hot_ice, carbohydrate, mainFood, spicy"
				+ " FROM Menu";
		List<Menu> menulist = jt.query(sql, new MenuRowMapper());
		return menulist;
	}

	@Override
	public List<Menu> findMenuByClassify(Menuclassify menuclassify) {
		String sql = "SELECT id, menuName, weather, imgPath, soupy, hot_ice, carbohydrate, mainFood, spicy"
				+ " FROM Menu WHERE soupy = ? AND hot_ice = ? AND carbohydrate = ? AND mainFood = ? AND spicy = ?";
		List<Menu> menulist = jt.query(sql, new MenuRowMapper(), menuclassify.getSoupy(), menuclassify.getHot_ice(), menuclassify.getCarbohydrate(), menuclassify.getMainFood(), menuclassify.getSpicy());
		return menulist;
	}

	@Override
	public List<Menu> findMenuBywheater(int weather) {
		
		return null;
	}

	@Override
	public Menu findMenuById(long id) {
		String sql ="SELECT id, menuName, weather, imgPath, soupy, hot_ice, carbohydrate, mainFood, spicy"
				+ " FROM Menu WHERE id = ?";
		Menu menu = jt.queryForObject(sql, new MenuRowMapper(), id);
		return menu;
	}

	@Override
	public List<Menu> findMenuByMenuName(String menuName) {
		String sql ="SELECT id, menuName, weather, imgPath, soupy, hot_ice, carbohydrate, mainFood, spicy"
				+ " FROM Menu WHERE menuName = ?";
		List<Menu> menulist = jt.query(sql, new MenuRowMapper(),menuName);
		return menulist;
	}

}
