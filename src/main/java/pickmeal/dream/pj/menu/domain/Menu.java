package pickmeal.dream.pj.menu.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Menu {
	private long id;
	private String menuName;
	private int weather;
	private String imgPath;
	private Menuclassify menuclassify;
	
	public Menu() {
		
	}

	public Menu(long id, String menuName, int weather, String imgPath, Menuclassify menuclassify) {
		this.id = id;
		this.menuName = menuName;
		this.weather = weather;
		this.imgPath = imgPath;
		this.menuclassify = menuclassify;
	}
	
	public Menu(String menuName, int weather, String imgPath, Menuclassify menuclassify) {
		this.menuName = menuName;
		this.weather = weather;
		this.imgPath = imgPath;
		this.menuclassify = menuclassify;
	}
	
	@Override
	public String toString() {
		return "Menu [id=" + id + ", menuName=" + menuName + ", weather=" + weather + ", imgPath=" + imgPath
				+ ", menuclassify=" + menuclassify + "]";
	}
}
