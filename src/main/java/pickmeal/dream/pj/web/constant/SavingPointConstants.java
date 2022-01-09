package pickmeal.dream.pj.web.constant;

import lombok.Getter;

@Getter
public enum SavingPointConstants {
	SIGN_UP("회원가입 감사 적립", 1), 
	ATTENDANCE("1일 출석 적립", 1), 
	ATTENDANCE_7DAYS("7일 연속 출석 적립", 15), 
	ATTENDANCE_15DAYS("15일 연속 출석 적립", 40), 
	ATTENDANCE_30DAYS("30일 연속 출석 적립", 100), 
	PLAY_GAME("게임 적립", 1), 
	CHECK_VISIT("식당 방문 여부 확인 적립", 100), 
	REVIEW("리뷰 적립", 100), 
	WRITE_POST("게시글 적립", 70), 
	WRITE_COMMENT("댓글 적립", 10);
	
	private final String kor;
	private final int point;
	
	SavingPointConstants(String kor, int point) {
		this.kor = kor;
		this.point = point;
	}
}
