package pickmeal.dream.pj.restaurant.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pickmeal.dream.pj.member.domain.Member;

@Getter
@Setter
@ToString
public class VisitedRestaurant {
	private long id;
	private Member member;
	private Restaurant restaurant;
	boolean Review;
	private Date regDate;
	
	public VisitedRestaurant() {
		
	}
	
	
	public VisitedRestaurant(Member member, Restaurant restaurant) {
		super();
		this.member = member;
		this.restaurant = restaurant;
	}

	public VisitedRestaurant(long id, Member member, Restaurant restaurant, boolean review, Date regDate) {
		super();
		this.id = id;
		this.member = member;
		this.restaurant = restaurant;
		Review = review;
		this.regDate = regDate;
	}
}
