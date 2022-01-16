package pickmeal.dream.pj.member.util;

import org.springframework.stereotype.Component;

import lombok.extern.java.Log;
import pickmeal.dream.pj.member.domain.Member;

@Component
@Log
public class PasswordDecoding extends PasswordCipher {
	/**
	 * 실제로 부르는 메소드
	 * 암호화된 문자를 평문으로 변환
	 * @param member
	 * @return
	 */
	public String getResult(Member member) {
		int email = member.getEmail().split("@")[0].length();
		String passwd = member.getPasswd();
		String transPasswd = transform(email, passwd);
		
		return transPasswd;
	}
	
	/**
	 * 아이디 자릿수에 따른 비밀번호 평문으로 치환
	 * @param email
	 * @param passwd
	 * @return
	 */
	private String transform(int email, String passwd) {
		int pwl = getWordNumber(passwd.charAt(0)); // 원래 비밀번호가 몇 자리인가?
		// 2개 이하일 경우 그냥 하면 되고, 3개 이상일 경우 나머지 더미 문자를 다 지워줘야한다.
		int digit = getWordNumber(passwd.charAt(1)) - pwl; // 각 문자 1개당 몇개의 문자로 이루어져있는가?
		String result = passwd.substring(2); // 구분 문자를 지워준다.
		String decoding = "";

		int jump = 0; // 원래 문자로 돌릴 때 각 자릿수를 어떻게 띄울 지
		
		// 비밀번호에 관련된 문자만 남도록 잘라준다.
		if (digit > 2) {
			result = result.substring(0, digit * pwl); // 각 문자당 자리수가 3개 이상일 경우 비밀번호 자릿수 * 각 문자 자릿수 한 것만큼 끊어준다.
			jump = digit;
		} else {
			result = result.substring(0, 2 * pwl); // 2개 이하일 경우 무조건 2자리로 들어간다.
			jump = 2;
		}
		
		// 각 자리수마다 원래 문자로 돌려줘야한다.
		for (int i=0; i<result.length(); i += jump) {
			String beforeStr = result.substring(i, i+2); // 원복 전 문자 1개 (몇 자리든 처음 두 문자가 가장 중요하다)
			int digitNum = 0;
			if (i != 0) {
				digitNum = i / jump;
			}
			int type = getPasswdType(beforeStr);
			decoding += convertToOrigin(type, beforeStr.charAt(0), email, digitNum);
		}
		
		return decoding;
	}
	
	/**
	 * 타입 별로 평문을 치환한다.
	 * @param type
	 * @param before
	 * @param delay
	 * @param i
	 * @return
	 */
	private String convertToOrigin(int type, char before, int delay, int i) {
		int beforeNum = getWordNumber(before);
		char after = 0;
		beforeNum = beforeNum - delay - i;
		while (beforeNum < 0) {
			beforeNum += upperArr.length;
		}
		// 문자를 원래 문자로 변환한다.
		if (type == 4) { // 특수 문자인 경우 부모 클래스 배열 내에서 찾는다.
			after = allowArr[beforeNum];
		} else { // 나머지의 경우 ascii 코드 내에서 찾는다.
			if (type == 1) {
				beforeNum += 48;
			} else if (type == 2) {
				beforeNum += 65;
			} else if (type == 3) {
				beforeNum += 97;
			}
			after = (char)beforeNum;
		}
		
		return String.valueOf(after);
	}
	
	/**
	 * 해당 문자의 타입 반환
	 * 1 : 숫자 / 2 : 대문자 / 3 : 소문자 / 4 : 특수문자
	 * @param beforeStr
	 * @return
	 */
	private int getPasswdType(String beforeStr) {
		char first = beforeStr.charAt(0);
		char second = beforeStr.charAt(1);
		int firstNum = getWordNumber(first); // 첫번째 글자가 몇번째로 있는지
		int secondNum = getWordNumber(second); // 두번째 글자가 몇번째로 있는지
		int type = secondNum - firstNum;
		
		if (type < 0) { // 만일 타입이 숫자가 더 작다면 배열보닥 컸기 때문에 더 작은 것이므로 배열 길이만큼 더한다.
			type += upperArr.length;
		}
		
		return type;
	}
	
	/**
	 * 해당 단어가 배열에서 몇번째 자리인지 반환해준다.
	 * @param word
	 * @return
	 */
	private int getWordNumber(char word) {
		int wordNum = 0;
		
		for (int i=0; i<upperArr.length; i++) {
			if (word == upperArr[i]) {
				wordNum = i;
				break;
			}
		}
		
		return wordNum;
	}
}
