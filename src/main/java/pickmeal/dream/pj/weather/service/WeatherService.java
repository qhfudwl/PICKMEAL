package pickmeal.dream.pj.weather.service;

import java.net.HttpURLConnection;
import java.util.HashMap;

import pickmeal.dream.pj.weather.domain.Forecast;
import pickmeal.dream.pj.weather.domain.MyLocation;
import pickmeal.dream.pj.weather.domain.PickMealWeather;
import pickmeal.dream.pj.weather.domain.Weather;

public interface WeatherService {
	Weather getWeather(MyLocation myLocation);
	
	Weather getWeather(String nx, String ny, String date, String hour, String minute);
	
	PickMealWeather getPickMealTypeWeather(Weather weather);
	
	void getShortTermWeather(HashMap<String, String> categoryAndValue, String url, String date, String hour, String minute, String nx, String ny, String... reqCodes);
	
	void getShortTermLiveWeather(HashMap<String, String> categoryAndValue, String url, String date, String hour, String minute, String nx, String ny, String... reqCodes);
	
	String toStringHour(String hour, int calcNum);
	
	String[] getWeatherApiInfo(String url);
	
	void getCategoryAndValue(String[] itemPieces, String[] reqCodes, HashMap<String, String> categoryAndValue);
	
	HttpURLConnection connect(String apiUrl);

	Forecast getForecast(MyLocation ml);
}
