package pickmeal.dream.pj.weather.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Forecast {
	private List<PickMealWeather> pmwList;
}
