package pickmeal.dream.pj.web.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

@Component("presentTime")
@Log
public class PresentTime {
	
	// timestamp 타입.
	public Timestamp bringPresentTime() {
		LocalDateTime now = LocalDateTime.now();
		Timestamp nowTime = java.sql.Timestamp.valueOf(now);
		
		log.info("현재시간 : "+ nowTime);
		
		return nowTime; 
	}
	
	// Date 타입 현재 날짜
	public Date bringPresentTimeTypeDate() {
		LocalDate now = LocalDate.now();
		Date nowTime = Date.valueOf(now);
		
		return nowTime; 
	}
	
}
