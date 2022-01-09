package pickmeal.dream.pj.menu.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Menu {
	private long id;
	private String Menuname;
	private int weather;
	private String imgPath;
	private Menuclassify menuclassify;
	
	public Menu() {
		
	}

	public Menu(long id, String Menuname, int weather, String imgPath, Menuclassify menuclassify) {
		this.id = id;
		this.Menuname = Menuname;
		this.weather = weather;
		this.imgPath = imgPath;
		this.menuclassify = menuclassify;
	}
	
	public Menu(String Menuname, int weather, String imgPath, Menuclassify menuclassify) {
		this.Menuname = Menuname;
		this.weather = weather;
		this.imgPath = imgPath;
		this.menuclassify = menuclassify;
	}
	
	@Override
	public String toString() {
		return "Menu [id=" + id + ", Menuname=" + Menuname + ", weather=" + weather + ", imgPath=" + imgPath
				+ ", menuclassify=" + menuclassify + "]";
	}
}
