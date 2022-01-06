package pickmeal.dream.pj.web.util;

import org.springframework.stereotype.Component;
/**
 * 유효성 검사
 * @author 김보령
 *
 */
@Component("validator")
public class Validator {
	
	/**
	 * 빈 객체인지 확인
	 * @param obj
	 * @return
	 * @author 김보령
	 */
	public boolean isEmpty(Object obj) {
		if(obj == null || obj.equals("")) {
			return true;
		}
		return false;
	}
	
	public boolean isEmpty(double d) {
		if(d == 0) {
			return true;
		}
		return false;
	}
	
	public boolean isEmpty(long l) {
		if(l == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 현재 받은 문자열이 숫자인지 확인
	 * @param str
	 * @return
	 * @author 김보령
	 */
	public boolean isNumber(String str) {
		char[] cArr = {};
		// 끝에 소수점인 .0 이 붙은 경우
		if (str.substring(str.length()-2, str.length()).equals(".0")) {
			cArr = str.substring(0, str.length()-2).toCharArray();
		} else { // 그냥 들어올 경우
			cArr = str.toCharArray();
		}
		for (char c : cArr) {
			if (c < 48 || c > 57) {
				return false;
			}
		}
		return true;
	}
}
