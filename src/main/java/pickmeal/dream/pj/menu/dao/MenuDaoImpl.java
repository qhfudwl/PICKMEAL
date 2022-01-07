package pickmeal.dream.pj.menu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pickmeal.dream.pj.menu.domain.Menu;
import pickmeal.dream.pj.menu.domain.Menuclassify;

@Repository("menuDao")
public class MenuDaoImpl implements MenuDao{
	
	@Resource(name="jdbcTemplate")
	private JdbcTemplate jt;
	
	@Override
	public void addMenu(Menu menu) {
		String sql = "INSERT INTO Menu(Menuname, weather, imgPath, soupy, hot_ice, carhobydrate, mainFood, spicy)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		jt.update(sql, menu.getMenuname(),menu.getWeather(), menu.getImgPath(),
				menu.getMenuclassify().getSoupy(), menu.getMenuclassify().getHot_ice(), menu.getMenuclassify().getCarhobydrate(),
				menu.getMenuclassify().getMainFood(), menu.getMenuclassify().getSpicy());
		
	}

	@Override
	public List<Menu> findAllMenus() {
		String sql = "SELECT id, Menuname, weather, imgPath, soupy, hot_ice, carhobydrate, mainFood, spicy"
				+ " FROM Menu";
		List<Menu> menus = jt.query(sql, new MenuRowMapper());
		return menus;
	}

	@Override
	public List<Menu> findMenuByClassify(Menuclassify menuclassify) {
		String sql = "SELECT id, Menuname, weather, imgPath, soupy, hot_ice, carhobydrate, mainFood, spicy"
				+ "FROM Menu WHERE soupy = ? AND hot_ice = ? AND carhobydrate = ? AND mainFood = ? AND spicy = ?";
		List<Menu> menus = jt.query(sql, new MenuRowMapper(),menuclassify);
		return menus;
	}

	@Override
	public List<Menu> findMenuBywheater(int weather) {
		
		return null;
	}

}
