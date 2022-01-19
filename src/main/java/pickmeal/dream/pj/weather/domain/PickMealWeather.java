package pickmeal.dream.pj.weather.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PickMealWeather {
	private int temperature;
	private int sky;
	
	public PickMealWeather() {
		
	}

	public PickMealWeather(int temperature, int sky) {
		super();
		this.temperature = temperature;
		this.sky = sky;
	}
	
}
