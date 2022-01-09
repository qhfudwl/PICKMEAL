package pickmeal.dream.pj.member.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pickmeal.dream.pj.web.constant.SavingPointConstants;
import static pickmeal.dream.pj.web.constant.SavingPointConstants.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FoodPowerPointItem {
	private long id;
	private Member member;
	private int point;
	private SavingPointConstants detail;
	private Date regDate;
	
	public SavingPointConstants convertToSavingPointConstants(int num) {
		SavingPointConstants spc = null;
		switch(num) {
		case 0:
			spc = SIGN_UP;
			break;
		case 1:
			spc = ATTENDANCE;
			break;
		case 2:
			spc = ATTENDANCE_7DAYS;
			break;
		case 3:
			spc = ATTENDANCE_15DAYS;
			break;
		case 4:
			spc = ATTENDANCE_30DAYS;
			break;
		case 5:
			spc = PLAY_GAME;
			break;
		case 6:
			spc = CHECK_VISIT;
			break;
		case 7:
			spc = REVIEW;
			break;
		case 8:
			spc = WRITE_POST;
			break;
		case 9:
			spc = WRITE_COMMENT;
			break;
		}
		return spc;
	}
}
