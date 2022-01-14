package pickmeal.dream.pj.web.constant;

import lombok.Getter;

@Getter
public enum Constants {
	LEVEL0(0, "nonUser"),
	LEVEL1(300, "level1User"),
	LEVEL2(1000, "level2User"),
	LEVEL3(5000, "level3User"),
	LEVEL4(15000, "level4User"),
	LEVEL5(50000, "level5User"),
	GOOD(5), // 좋아요
	BAD(-5), // 나빴어요
	NORMAl(0), // 보통이였어요
	SIGN_UP_MANNER(36.5), // 회원 가입 시 매너 온도
	COMMENT_LIST(5); // 댓글 목록 개수
	
	private final double point;
	private final String imgPath;
	private final int num;
	
	Constants(double point) {
		this.point = point;
		this.imgPath = "";
		this.num = 0;
	}
	
	Constants(double point, String imgPath){
		this.point = point;
		this.imgPath = imgPath;
		this.num = 0;
	}

	Constants(int num) {
		this.point = 0;
		this.imgPath = "";
		this.num = num;
	}
}
