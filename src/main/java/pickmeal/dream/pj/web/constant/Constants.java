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
	GOOD(5),
	BAD(-5),
	NORMAl(0),
	SIGN_UP_MANNER(36.5);
	
	private final double point;
	private final String imgPath;
	
	Constants(double point) {
		this.point = point;
		this.imgPath = "";
	}
	
	Constants(double point, String imgPath){
		this.point = point;
		this.imgPath = imgPath;
	}
}
