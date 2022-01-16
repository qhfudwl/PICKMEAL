package pickmeal.dream.pj.member.util;

public class PasswordCipher {
	// /^[A-Za-z0-9!@#$%^~*+=&-]*$/
	// 최소 4자리 이상 최대 20자리 이하
	protected char[] allowArr = {'!', '@', '#', '$', '%', '^', '~', '*', '+', '=', '&', '-'}; // 허용할 특수문자
	protected char[] upperArr = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
	protected String lipi = "[LIPI]";
}
