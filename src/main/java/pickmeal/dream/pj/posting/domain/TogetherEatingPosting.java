package pickmeal.dream.pj.posting.domain;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.restaurant.domain.Restaurant;
/**
 * 
 * @author 윤효심
 *
 */
@Getter
@Setter
@ToString
public class TogetherEatingPosting extends Posting{
	
	private Date time;
	private boolean recruitment;
	private boolean mealChk;
	
	public TogetherEatingPosting() {
		
	}
	
	public TogetherEatingPosting(long id, Member member, Restaurant restaurant, char category, String title, String content, int commentsNumber, int likes, int views, Date regDate, Date time, boolean recruitment, boolean mealChk) {
		super(id, member, restaurant, category, title, content,commentsNumber, likes, views, regDate);
		this.time = time;
		this.recruitment = recruitment;
		this.mealChk = mealChk;
	}
}
