package pickmeal.dream.pj.member.util;

import org.springframework.stereotype.Component;

@Component
public class PasswordCipher {
	// /^[A-Za-z0-9!@#$%^~*+=&-]*$/
	// 최소 4자리 이상 최대 20자리 이하
	public char[] allowArr = {'!', '@', '#', '$', '%', '^', '~', '*', '+', '=', '&', '-'}; // 허용할 특수문자
	public char[] numberArr = {'1', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	public char[] upperArr = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	public char[] smallArr = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	protected String lipi = "[LIPI]";
}
