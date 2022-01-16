package pickmeal.dream.pj.member.util;

import org.springframework.stereotype.Component;

import lombok.extern.java.Log;
import pickmeal.dream.pj.member.domain.Member;

@Component
@Log
public class PasswordEncoding extends PasswordCipher {
	/**
	 * 실제로 부르는 메소드
	 * 평문을 암호화한 문자로 변환
	 * @param member
	 * @return
	 */
	public Member convertPassword(Member member) {
		int email = member.getEmail().split("@")[0].length();
		String passwd = member.getPasswd();
		String transPasswd = transform(email, passwd);
		member.setPasswd(transPasswd);
		
		return member;
	}
	
	/**
	 * 아이디 자릿수에 다라 비밀번호를 암호문으로 치환
	 * @param email
	 * @param passwd
	 * @return
	 */
	private String transform(int email, String passwd) {
		int pwl = passwd.length(); // 비밀전호 자릿수
		int times = 50 / pwl; // 각 문자당 몇개를 적어야하는지
		String firstStr = String.valueOf(upperArr[pwl]); // 첫번째 글자는 비밀번호 자릿수이다.
		String secondStr = String.valueOf(upperArr[pwl + times]); // 각 문자는 몇개로 치환할 건지 첫번째 문자로부터 몇개 띄어져있는지 체크
		char[] pwArr = getPasswdArr(passwd); // 비밀번호를 char 로 변환
		String result = ""; // 반환되는 비밀번호는 50문자이다.
		
		for (int i=0; i<pwl; i++) {
			int pwWord = (int)pwArr[i];
			int type = 0;
			if (pwWord >= 48 && pwWord <= 57) { // 숫자일 때
				type = 1;
			} else if (pwWord >= 65 && pwWord <= 90) { // 대문자일 때
				type = 2;
			} else if (pwWord >= 97 && pwWord <= 122) { // 소문자일 때
				type = 3;
			} else { // 특수문자일 때
				type = 4;
			}
			result += convertToCipher(type, i, pwArr[i], email, times);
		}
		
		result = firstStr + secondStr + mekedFinished(result) + lipi;
		
		return result;
	}
	
	/**
	 * 숫자 변환
	 * @param i
	 * @param type
	 * @param word
	 * @param delay
	 * @return
	 */
	private String convertToCipher(int type, int i, char word, int delay, int times) {
		int numWord = word; // 치환할 문자 ascii 코드값
		String dummy = "";
		// 문자 번호 확인
		if (type == 4) {
			// 특수 문자 배열에서 이 문자가 어떤 문자인지 확인해야한다.
			for (int j=0; j<allowArr.length; j++) {
				if (allowArr[j] == word) {
					numWord = j;
					break;
				}
			}
		} else {// 치환할 문자가 ascii 코드에서 해당 범위의 몇번 째인지
			if (type == 1) {
				numWord -= 48; 
			} else if (type == 2) {
				numWord -= 65;
			} else if (type == 3) {
				numWord -= 97;
			}
		}
		int inputWordNum = numWord + delay + i; // 배열에서 골라야 할 문자 번호 // 각 자리수에 해당하는 index 만큼 더해준다(같은 문자라도 다른 결과값을 위해서이다)
		char[] resultArr = new char[2];
		
		while (inputWordNum > upperArr.length-1) { // 만일 골라야할 문자 번호가 배열의 크기보다 클 경우 30 이라면 배열 길이를 빼서 4번째 문자를 고르자
			inputWordNum -= upperArr.length;
		}
		// 해당 문자가 어떤 타입인지
		int typeWordNum = inputWordNum + type;
		
		if (typeWordNum > upperArr.length-1) { // 만일 타입 숫자가 배열 크기보다 클 경우 배열 길이를 뺀다.
			typeWordNum -= upperArr.length;
		}
		
		resultArr[0] = upperArr[inputWordNum]; // 첫번째 문자는 현재 변환시켜야 할 문자를 변환하고
		resultArr[1] = upperArr[typeWordNum]; // 두번째 문자는 이 문자가 원래 어떤 타입인지를 지정한다.
		
		if (times > 2) { // 만약 각 문자가 3자리수 이상으로 만들어져야할 경우 더미 문자를 만들어 뒤에 붙힌다.
			dummy += randomStr(times - 2);
		}
		
		String result = String.valueOf(resultArr) + dummy;
		
		return result; // 2개의 문자로 만든 것을 반환
	}
	
	/**
	 * 완성 코드로 생성 (나머지 빈 자리를 채우자)
	 * 총 50문자가 나와야한다
	 * @param beforeStr
	 * @return
	 */
	private String mekedFinished(String beforeStr) {
		int beforeL = beforeStr.length();
		String afterStr = "";
		if (beforeL < 50) {
			afterStr = randomStr(50 - beforeL);
		}
		String resultStr = beforeStr + afterStr;
		
		return resultStr;
	}
	
	/**
	 * 나머지 빈 칸을 랜덤 글자로 채운다.
	 * @param required
	 * @return
	 */
	private String randomStr(int required) {
		String requiredStr = "";
		
		for (int n=0; n<required; n++) {
			int randomNum = (int)Math.floor(Math.random() * upperArr.length); // 배열의 길이만큼의 숫자 반환 (index 용이다)
			requiredStr += upperArr[randomNum];
		}
		
		return requiredStr;
	}
	
	/**
	 * 각 비밀번호 한자리씩을 char[] 로 변형
	 * @param passwd
	 * @return
	 */
	private char[] getPasswdArr(String passwd) {
		String[] pwArr = passwd.split("");
		int pwl = passwd.length();
		char[] transArr = new char[pwl];
		for (int i=0; i<pwl; i++) {
			transArr[i] = pwArr[i].charAt(0);
		}
		return transArr;
	}
}
