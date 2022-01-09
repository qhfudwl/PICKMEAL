package pickmeal.dream.pj.web.constant;

import lombok.Getter;

@Getter
public enum Constants {
	LEVEL1(300),
	LEVEL2(1000),
	LEVEL3(5000),
	LEVEL4(15000),
	LEVEL5(50000),
	GOOD(5),
	BAD(-5),
	NORMAl(0),
	SIGN_UP_MANNER(36.5);
	
	private final double point;
	
	Constants(double point) {
		this.point = point;
	}
}
