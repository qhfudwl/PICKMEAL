package pickmeal.dream.pj.menu.domain;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Menuclassify {
	private int soupy;
	private int hot_ice;
	private int carbohydrate;
	private int mainFood;
	private int spicy;
	
public Menuclassify() {
		
	}
	public Menuclassify(int soupy, int hot_ice, int carbohydrate, int mainFood, int spicy) {
		super();
		this.soupy = soupy;
		this.hot_ice = hot_ice;
		this.carbohydrate = carbohydrate;
		this.mainFood = mainFood;
		this.spicy = spicy;
	}

	@Override
	public String toString() {
		return "[soupy=" + soupy + ", hot_ice=" + hot_ice + ", carbohydrate=" + carbohydrate
				+ ", mainFood=" + mainFood + ", spicy=" + spicy + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(carbohydrate, hot_ice, mainFood, soupy, spicy);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Menuclassify other = (Menuclassify) obj;
		return carbohydrate == other.carbohydrate && hot_ice == other.hot_ice && mainFood == other.mainFood
				&& soupy == other.soupy && spicy == other.spicy;
	}
}
