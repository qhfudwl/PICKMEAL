package pickmeal.dream.pj.menu.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuClassifyCommand {
	private int soupy;
	private int hot_ice;
	private int carbohydrate;
	private int mainFood;
	private int spicy;
	@Override
	public String toString() {
		return "MenuClassifyCommand [soupy=" + soupy + ", hot_ice=" + hot_ice + ", carbohydrate=" + carbohydrate
				+ ", mainFood=" + mainFood + ", spicy=" + spicy + "]";
	}
}
