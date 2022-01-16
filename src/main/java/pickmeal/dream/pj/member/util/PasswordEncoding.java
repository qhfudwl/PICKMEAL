package pickmeal.dream.pj.member.util;

import javax.crypto.Cipher;

import org.springframework.stereotype.Component;

import lombok.extern.java.Log;
import pickmeal.dream.pj.member.domain.Member;

@Component
@Log
public class PasswordEncoding extends Password {
	
	public String getResult(Member member) {
		int email = member.getEmail().split("@")[0].length();
		String passwd = member.getPasswd();
		String transPasswd = transform(email, passwd);
		
		return transPasswd;
	}
	
	public static void main(String[] args) {
		Member m = new Member();
		m.setEmail("qhfudwl@naver.com");
		m.setPasswd("999ABZabz!@#-");
		PasswordEncoding pe = new PasswordEncoding();
		String result = pe.getResult(m);

		log.info("비밀번호 자리수 : " + m.getPasswd().length());
		log.info("문자 하나당 치환 문자 수 : " + 50 / m.getPasswd().length());
		log.info(result);
	}
	
	private String transform(int email, String passwd) {
		int pwl = passwd.length(); // 비밀전호 자릿수
		int times = 50 / pwl; // 각 문자당 몇개를 적어야하는지
		String firstStr = String.valueOf(upperArr[pwl]); // 첫번째 글자는 비밀번호 자릿수이다.
		String secondStr = String.valueOf(upperArr[pwl + times]); // 각 문자는 몇개로 치환할 건지 첫번째 문자로부터 몇개 띄어져있는지 체크
		char[] pwArr = getPasswdArr(passwd); // 비밀번호를 char 로 변환
		String result = firstStr + secondStr; // 반환되는 비밀번호는 50문자이다.
		
		for (int i=0; i<pwl; i++) {
			int pwWord = (int)pwArr[i];
			if (pwWord >= 48 && pwWord <= 57) { // 숫자일 때
				result += numberTransform(i, pwArr[i], email, times);
			} else if (pwWord >= 65 && pwWord <= 90) { // 대문자일 때
				result += bigTransform(i, pwArr[i], email, times);
			} else if (pwWord >= 97 && pwWord <= 122) { // 소문자일 때
				result += smallTransform(i, pwArr[i], email, times);
			} else { // 특수문자일 때
				result += specifyTransform(i, pwArr[i], email, times);
			}
		}
		
		result = mekedFinished(result) + lipi;
		
		return result;
	}
	
	/**
	 * 완성 코드로 생성 (나머지 빈 자리를 채우자)
	 * 총 50문자가 나와야한다
	 * @param beforeStr
	 * @return
	 */
	private String mekedFinished(String beforeStr) {
		int beforeL = beforeStr.length();
		String afterStr = null;
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
	 * 숫자 변환
	 * @param i
	 * @param type
	 * @param word
	 * @param delay
	 * @return
	 */
	private String numberTransform(int i, char word, int delay, int times) {
		int numWord = word; // 치환할 문자
		String dummy = "";
		numWord -= 47; // 치환할 문자가 ascii 코드에서 해당 범위의 몇번 째인지
		int inputWordNum = numWord + delay; // 배열에서 골라야 할 문자 번호
		char[] resultArr = new char[2];
		
		inputWordNum += i; // 각 자리수에 해당하는 index 만큼 더해준다(같은 문자라도 다른 결과값을 위해서이다)
		
		if (inputWordNum > upperArr.length) { // 만일 골라야할 문자 번호가 배열의 크기보다 클 경우 30 이라면 배열 길이를 빼서 4번째 문자를 고르자
			inputWordNum -= upperArr.length;
		}
		int typeWordNum = inputWordNum + 1; // 해당 문자가 어떤 타입인지
		log.info("데이터 타입 숫자 전 : " + typeWordNum);
		if (typeWordNum > upperArr.length) { // 만일 타입 숫자가 배열 크기보다 클 경우 배열 길이를 뺀다.
			typeWordNum -= upperArr.length;
		}
		log.info("데이터 타입 숫자 후 : " + typeWordNum);
		
		// 여기서 -1 을 해주는 이유는 배열의 index 가 0부터 시작하기 때문이다.
		resultArr[0] = upperArr[inputWordNum-1]; // 첫번째 문자는 현재 변환시켜야 할 문자를 변환하고
		resultArr[1] = upperArr[typeWordNum-1]; // 숫자일 경우 바로 다음 문자가 붙어있다.
		
		if (times > 2) { // 만약 각 문자가 3자리수 이상으로 만들어져야할 경우 더미 문자를 만들어 뒤에 붙힌다.
			dummy += randomStr(times - 2);
		}
		
		String result = String.valueOf(resultArr) + dummy;
		
		return result; // 2개의 문자로 만든 것을 반환
	}
	/**
	 * 대문자 변환
	 * @param i
	 * @param type
	 * @param word
	 * @param delay
	 * @return
	 */
	private String bigTransform(int i, char word, int delay, int times) {
		int numWord = word; // 치환할 문자
		String dummy = "";
		numWord -= 64; // 치환할 문자가 ascii 코드에서 해당 범위의 몇번 째인지
		int inputWordNum = numWord + delay; // 배열에서 골라야 할 문자 번호
		char[] resultArr = new char[2];
		
		if (inputWordNum > upperArr.length) { // 만일 골라야할 문자 번호가 배열의 크기보다 클 경우 30 이라면 배열 길이를 빼서 4번째 문자를 고르자
			inputWordNum -= upperArr.length;
		}
		
		inputWordNum += i;
		
		if (inputWordNum > upperArr.length) { // 만일 골라야할 문자 번호가 배열의 크기보다 클 경우 30 이라면 배열 길이를 빼서 4번째 문자를 고르자
			inputWordNum -= upperArr.length;
		}

		int typeWordNum = inputWordNum + 2; // 해당 문자가 어떤 타입인지
		if (typeWordNum > upperArr.length) { // 만일 타입 숫자가 배열 크기보다 클 경우 배열 길이를 뺀다.
			typeWordNum -= upperArr.length;
		}
		
		resultArr[0] = upperArr[inputWordNum-1]; // 첫번째 문자는 현재 변환시켜야 할 문자를 변환하고
		resultArr[1] = upperArr[typeWordNum-1]; // 숫자일 경우 2번째 뒤 문자가 붙어있다.
		
		if (times > 2) { // 만약 각 문자가 3자리수 이상으로 만들어져야할 경우 더미 문자를 만들어 뒤에 붙힌다.
			dummy += randomStr(times - 2);
		}
		
		String result = String.valueOf(resultArr) + dummy;
		
		return result; // 2개의 문자로 만든 것을 반환
	}
	/**
	 * 소문자 변환
	 * @param i
	 * @param type
	 * @param word
	 * @param delay
	 * @return
	 */
	private String smallTransform(int i, char word, int delay, int times) {
		int numWord = word; // 치환할 문자
		String dummy = "";
		numWord -= 96; // 치환할 문자가 ascii 코드에서 해당 범위의 몇번 째인지
		int inputWordNum = numWord + delay; // 배열에서 골라야 할 문자 번호
		char[] resultArr = new char[2];
		
		if (inputWordNum > upperArr.length) { // 만일 골라야할 문자 번호가 배열의 크기보다 클 경우 30 이라면 배열 길이를 빼서 4번째 문자를 고르자
			inputWordNum -= upperArr.length;
		}
		
		inputWordNum += i;
		
		if (inputWordNum > upperArr.length) { // 만일 골라야할 문자 번호가 배열의 크기보다 클 경우 30 이라면 배열 길이를 빼서 4번째 문자를 고르자
			inputWordNum -= upperArr.length;
		}

		int typeWordNum = inputWordNum + 3; // 해당 문자가 어떤 타입인지
		if (typeWordNum > upperArr.length) { // 만일 타입 숫자가 배열 크기보다 클 경우 배열 길이를 뺀다.
			typeWordNum -= upperArr.length;
		}
		
		resultArr[0] = upperArr[inputWordNum-1]; // 첫번째 문자는 현재 변환시켜야 할 문자를 변환하고
		resultArr[1] = upperArr[typeWordNum-1];
		
		if (times > 2) { // 만약 각 문자가 3자리수 이상으로 만들어져야할 경우 더미 문자를 만들어 뒤에 붙힌다.
			dummy += randomStr(times - 2);
		}
		
		String result = String.valueOf(resultArr) + dummy;
		
		return result; // 2개의 문자로 만든 것을 반환
	}
	/**
	 * 특수문자 변환
	 * @param i
	 * @param type
	 * @param word
	 * @param delay
	 * @return
	 */
	private String specifyTransform(int i, char word, int delay, int times) {
		int numWord = 0; // 치환할 문자
		String dummy = "";
		// 특수 문자 배열에서 이 문자가 어떤 문자인지 확인해야한다.
		for (int j=0; j<allowArr.length; j++) {
			if (allowArr[j] == word) {
				numWord = j;
				break;
			}
		}
		int inputWordNum = numWord + delay; // 배열에서 골라야 할 문자 번호
		char[] resultArr = new char[2];
		
		if (inputWordNum > upperArr.length) { // 만일 골라야할 문자 번호가 배열의 크기보다 클 경우 30 이라면 배열 길이를 빼서 4번째 문자를 고르자
			inputWordNum -= upperArr.length;
		}
		
		inputWordNum += i;
		
		if (inputWordNum > upperArr.length) { // 만일 골라야할 문자 번호가 배열의 크기보다 클 경우 30 이라면 배열 길이를 빼서 4번째 문자를 고르자
			inputWordNum -= upperArr.length;
		}

		int typeWordNum = inputWordNum + 4; // 해당 문자가 어떤 타입인지
		if (typeWordNum > upperArr.length) { // 만일 타입 숫자가 배열 크기보다 클 경우 배열 길이를 뺀다.
			typeWordNum -= upperArr.length;
		}
		
		resultArr[0] = upperArr[inputWordNum-1]; // 첫번째 문자는 현재 변환시켜야 할 문자를 변환하고
		resultArr[1] = upperArr[typeWordNum-1]; // 숫자일 경우 바로 다음 문자가 붙어있다.
		
		if (times > 2) { // 만약 각 문자가 3자리수 이상으로 만들어져야할 경우 더미 문자를 만들어 뒤에 붙힌다.
			dummy += randomStr(times - 2);
		}
		
		String result = String.valueOf(resultArr) + dummy;
		
		return result; // 2개의 문자로 만든 것을 반환
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
