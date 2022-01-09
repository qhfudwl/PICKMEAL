package pickmeal.dream.pj.member.domain;

import static pickmeal.dream.pj.web.constant.SavingPointConstants.*;
import static pickmeal.dream.pj.web.constant.Constants.*;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pickmeal.dream.pj.web.constant.Constants;
import pickmeal.dream.pj.web.constant.SavingPointConstants;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	private long id;
	private char memberType;
	private String email;
	private String passwd;
	private String nickName;
	private String birth;
	private char gender;
	private String profileImgPath;
	private Date regDate;
	private int foodPowerPoint;
	private double mannerTemperature;
	private int attendence;
	
	public void saveFoodPowerPoint(SavingPointConstants spc) {
		switch(spc) {
		case SIGN_UP:
			this.foodPowerPoint = SIGN_UP.getPoint();
			break;
		case ATTENDANCE:
			this.foodPowerPoint += ATTENDANCE.getPoint();
			break;
		case ATTENDANCE_7DAYS:
			this.foodPowerPoint += ATTENDANCE_7DAYS.getPoint();
			break;
		case ATTENDANCE_15DAYS:
			this.foodPowerPoint += ATTENDANCE_15DAYS.getPoint();
			break;
		case ATTENDANCE_30DAYS:
			this.foodPowerPoint += ATTENDANCE_30DAYS.getPoint();
			break;
		case PLAY_GAME:
			this.foodPowerPoint += PLAY_GAME.getPoint();
			break;
		case CHECK_VISIT:
			this.foodPowerPoint += CHECK_VISIT.getPoint();
			break;
		case REVIEW:
			this.foodPowerPoint += REVIEW.getPoint();
			break;
		case WRITE_POST:
			this.foodPowerPoint += WRITE_POST.getPoint();
			break;
		case WRITE_COMMENT:
			this.foodPowerPoint += WRITE_COMMENT.getPoint();
			break;
		}
	}
	
	/**
	 * 회원 가입 시 매너 온도는 0 이기 때문에 36.5 로 맞춰준다.
	 * 그 후, 평가 시 좋아요를 받았다면 5도씩 올려준다.
	 * 싫어요의 경우 5도를 낮춘다.
	 * 보통이였어요는 그대로
	 */
	public void addMannerTemperature(Constants c) {
		switch(c) {
		case GOOD:
			this.mannerTemperature += GOOD.getPoint();
			break;
		case BAD:
			this.mannerTemperature += BAD.getPoint();
			break;
		case NORMAl:
			this.mannerTemperature += NORMAl.getPoint();
			break;
		case SIGN_UP_MANNER:
			this.mannerTemperature = SIGN_UP_MANNER.getPoint();
			break;
		}
	}
}
