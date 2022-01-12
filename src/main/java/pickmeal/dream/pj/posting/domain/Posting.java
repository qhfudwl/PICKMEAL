package pickmeal.dream.pj.posting.domain;

import java.util.Date;

import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.restaurant.domain.Restaurant;

@Getter
@Setter
@ToString
public class Posting {
	private long id;
	private Member member;
	private Restaurant restaurant;
	private char category; // N : 공지사항, R : 식당 추천, E : 밥친구
	private String title;
	private String content;
	private int commentsNumber;
	private int likes;
	private int views;
	private Date regDate;
	
	//생성자는 롬복보다는 필요한것만 만들어서 이유있게 쓸 것!
	public Posting() {}

	public Posting(long id, Member member, Restaurant restaurant, char category, String title, String content, int commentsNumber, int likes, int views, Date regDate) {
		
	}
	public Posting(long id) {
		super();
		this.id = id;
	}
}
