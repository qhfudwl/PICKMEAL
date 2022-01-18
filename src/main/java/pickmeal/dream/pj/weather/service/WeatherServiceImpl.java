package pickmeal.dream.pj.weather.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import pickmeal.dream.pj.menu.domain.Menu;
import pickmeal.dream.pj.menu.repository.MenuDaoImpl;
import pickmeal.dream.pj.weather.command.WeatherCommand;
import pickmeal.dream.pj.weather.domain.MyLocation;
import pickmeal.dream.pj.weather.domain.Weather;

/**
 * - 기온(T1H)	결과 : (실수값)℃
 * - 하늘상태(SKY)	결과 : 맑음(1), 구름많음(3), 흐림(4)
 * - 강수형태(PTY)	결과 : 없음(0), 비(1), 비/눈(2), 눈(3), 빗방울(5), 빗방울눈날림(6), 눈날림(7) 
 * 
 * 날씨 > 기본 = 0 / 맑음 = 1 / 구름 = 2 / 비 = 3 / 눈 = 4
 * 하늘상태 구름많음(3) or 흐림(4) 에서 강수형태 없음(0)일 경우 : 2
 * 하늘상태 상관없이 강수형태 비(1)이상 일 경우 : 3 or 4
 * 
 * 초단기예보 : 1시간30분 단위	ex) 1130 1230 1330 1430
 * 초단기실황 : 1시간 단위	ex) 1100 1200 1300 1400
 */

@Log
@Log4j
@Service("weatherService")
public class WeatherServiceImpl implements WeatherService {
	
	@Autowired
	private MenuDaoImpl menudao;

	private String Short_term_weather_url = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst";	// 초단기예보 : 매시간 30분 생성, 하늘상태
	private String Short_term_live_weather_url = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst"; // 초단기실황 : 매시간 30분 생성, 기온, 강수형태
	private String Short_term_forecase_weather_url = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"; // 단기예보 : 3시간단위 측정(02:00~23:00)
	
	private String service_key = "U5Gbgs6jQKOEWClQj8cmzxsDET3UIUEj4HUop%2Bz%2F2%2Fx2bN4gOFK54mAOshV7jEN3IeG%2FNT8tTjexU5OfyjY9Sg%3D%3D"; //API 일반키
	
	@Override
	public Weather getWeather(MyLocation myLocation) {
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String date = now.format(formatter);

		LocalTime nowTime = LocalTime.now();
		formatter = DateTimeFormatter.ofPattern("HHmm");
		String time = nowTime.format(formatter);
		String hour = time.substring(0, 2);
		String minute = time.substring(2);
		
		return getWeather(myLocation.getNx(), myLocation.getNy(), date, hour, minute);
	}
	@Override
	public Weather getWeather(String nx, String ny, String date, String hour, String minute) {
		Weather weather = new Weather();
		HashMap<String, String> categoryAndValue = new HashMap<String, String>();
		getShortTermWeather(categoryAndValue, Short_term_weather_url, date, hour, minute, nx, ny, "SKY");
		getShortTermLiveWeather(categoryAndValue, Short_term_live_weather_url, date, hour, minute, nx, ny, "T1H", "PTY");
		
		for(Entry<String, String> cav : categoryAndValue.entrySet()) {
			log.info(cav.getKey() + " " + cav.getValue());
			if(cav.getKey().equals("T1H")) {
				weather.setT1h(Double.parseDouble(cav.getValue()));
			} else if(cav.getKey().equals("SKY")) {
				weather.setSky(Integer.parseInt(cav.getValue()));
			} else if(cav.getKey().equals("PTY")) {
				weather.setPty(Integer.parseInt(cav.getValue()));
			}
			//필요하다면 더 추가
		}
		return weather;
	}
	@Override
	public WeatherCommand getPickMealTypeWeather(Weather weather) {
		log.info(weather.toString());
		WeatherCommand wc = new WeatherCommand();
		if(weather.getT1h() < 0) {
			wc.setTemperature((int)Math.floor(weather.getT1h()));
		} else {
			wc.setTemperature((int)Math.ceil(weather.getT1h()));
		}
		
		if(weather.getPty() > 0) {
			if(weather.getPty() == 1 || weather.getPty() == 5) {
				wc.setSky(3);
			} else if (weather.getPty() == 2 || weather.getPty() == 6) {
				wc.setSky(3);
			} else if (weather.getPty() == 3 || weather.getPty() == 7) {
				wc.setSky(4);
			}
			return wc;
		}
		
		if(weather.getSky() > 1) {
			wc.setSky(2);
		} else {
			wc.setSky(1);
		}
		return wc;
	}
	
	//메뉴서비스로 빼야할지도?
	@Override
	public void getMenuDependingOnTheWeather(WeatherCommand wc) {
		int temperature;
		if(wc.getTemperature() >= 25) {
			temperature = 1;
		}else if (wc.getTemperature() <= 10) {
			temperature = 2;
		}else {
			temperature = 0;
		}
		
		List<Menu> menuList = menudao.findMenuByWeather(temperature, wc.getSky());
		Random rand = new Random();
		wc.setMenuName(menuList.get(rand.nextInt(menuList.size())).getMenuName());
	}
	
	/**
	 * 초단기예보는 지정시간 기준 6시간 이후 까지 한시간 단위로 일기예보 결과가 출력된다.
	 * 지정시간에서 가장 가까운 시간으로 출력해서 날씨 정보 제공할 것.
	 * 
	 */
	@Override
	public void getShortTermWeather(HashMap<String, String> categoryAndValue, String url, String date, String hour, String minute, String nx, String ny, String... reqCodes) {
		int cm = Integer.parseInt(minute);
		/*
		 * checkMinute 기준시간(현재시간)이 30분 이전이라면 한 시간 전을 측정한다.
		 * ex) 현재시간 14시24분 : 13시30분 입력 14시 예보 출력
		 * ex) 현재시간 14시46분 : 14시30분 입력 15시 예보 출력
		 */
		if(cm < 30) { 
			hour = toStringHour(hour, -1);
		}
		
		url = url + "?serviceKey=" + service_key
				+ "&numOfRows=100"
				+ "&pageNo=1"
				+ "&base_date=" + date
				+ "&base_time=" + hour + "30"
				+ "&nx=" + nx	
				+ "&ny=" + ny
				+ "&dataType=JSON";
		
		String[] apiItemArray = getWeatherApiInfo(url); //사용하는 라인 자르기 + "", {}, [] 제거
		
		hour = toStringHour(hour, 1);
		for(String item : apiItemArray) { //사용하는 라인 나누기 ex) 실행결과 -> [basedate:xxx], [category:xxx], [nx:xx]
			if(item.indexOf(hour+"00") != -1) { //지정시간 기준 가장 가까운 시간의 값 도출
				String[] itemPieces = item.split(",");
				getCategoryAndValue(itemPieces, reqCodes, categoryAndValue);
			}
		}
	}
	
	/**
	 * 초단기실황 매시간30분에 측정 40분에 api로 제공.
	 * ex) 지정시간을 1100으로 하려면 11시40분 이후에 가능
	 */
	@Override
	public void getShortTermLiveWeather(HashMap<String, String> categoryAndValue, String url, String date, String hour, String minute, String nx, String ny, String... reqCodes) {
		int cm = Integer.parseInt(minute);
		/*
		 * checkMinute 기준시간(현재시간)이 30분 이전이라면 한 시간 전을 측정한다.
		 * ex) 현재시간 14시24분 : 13시00분 입력 13시30분에 측정된 실황 출력
		 * ex) 현재시간 14시46분 : 14시00분 입력 14시30분에 측정된 실황 출력
		 */
		if(cm < 30) { 
			hour = toStringHour(hour, -1);
		}
		
		url = url + "?serviceKey=" + service_key
			+ "&numOfRows=100"
			+ "&pageNo=1"
			+ "&base_date=" + date
			+ "&base_time=" + hour + "00"
			+ "&nx=" + nx	
			+ "&ny=" + ny
			+ "&dataType=JSON";
		
		String[] apiItemArray = getWeatherApiInfo(url); //사용하는 라인 자르기 + "", {}, [] 제거
		
		for(String item : apiItemArray) { //사용하는 라인 나누기 ex) 실행결과 -> [basedate:xxx], [category:xxx], [nx:xx]
			String[] itemPieces = item.split(",");
			getCategoryAndValue(itemPieces, reqCodes, categoryAndValue);
		}
	}

	//시간변환때 url 시간 입력에 네자리가 들어가도록 변환해줘야한다 ex) 930 -> 0930
	@Override
	public String toStringHour(String hour, int calcNum) {
		int calcHour = Integer.parseInt(hour) + calcNum;
		
		if(calcHour < 0) {
			return "23";
		} else if (calcHour >= 24) {
			return "00";
		}
		
		if(calcHour < 10) {
			return "0" + calcHour;
		}
		return Integer.toString(calcHour);
	}

	//JSON타입 1차 가공
	@Override
	public String[] getWeatherApiInfo(String url) {

		HttpURLConnection con = connect(url);
		
		String apiResponse = "";
		try {
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-type", "application/json");
			
			BufferedReader rd;
	        if(con.getResponseCode() >= 200 && con.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	        }
	        
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
//	        	System.out.println(line);
	            sb.append(line);
	        }

            rd.close();
            apiResponse = sb.toString();
            
		} catch (ProtocolException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} catch (IOException e) {
			throw new RuntimeException("IOException", e);
		} finally {
            con.disconnect();
        }
		
		//HTTP_ERROR 발생시 무조건XML 타입으로 반환이 온다.
		//HTTP_ERROR 발생시 resultCode 가 아닌 returnReasonCode 라고 나온다.
		apiResponse = apiResponse.replaceAll("\"", "");
		if(apiResponse.indexOf("resultCode:00") == -1) {
			log.info(url);
			log.info(apiResponse);
//			System.out.println(apiResponse.substring(apiResponse.indexOf("resultCode"), apiResponse.indexOf(",")));
		} else {
			apiResponse = apiResponse.replace("{", "");
			apiResponse = apiResponse.substring(apiResponse.indexOf("[")+1, apiResponse.lastIndexOf("}]"));
		}
		String[] result = apiResponse.split("},");
		
		return result;
	}
	
	/**
	 * 카테고리와 그에 해당하는 값만 HashMap타입으로 정리
	 */
	@Override
	public void getCategoryAndValue(String[] itemPieces, String[] reqCodes, HashMap<String, String> categoryAndValue) {
		String category = "";
		String value = "";
		String[] keyValue;
		
//		for(String code : reqCodes) {
//			System.out.println(code);
//		}
		
		for(String piece : itemPieces) {
			if(piece.indexOf("category") != -1) {
				keyValue = piece.split(":");
				category = keyValue[1];
			} else if (piece.indexOf("Value") != -1) {
				keyValue = piece.split(":");
				value = keyValue[1];
			}
			
			if(Arrays.asList(reqCodes).contains(category)) {
				categoryAndValue.put(category, value);
			}
		}
	}
	
	@Override
	public HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }
}
