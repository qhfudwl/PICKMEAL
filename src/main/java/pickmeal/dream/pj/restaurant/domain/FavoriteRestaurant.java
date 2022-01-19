package pickmeal.dream.pj.restaurant.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pickmeal.dream.pj.member.domain.Member;

@Getter
@Setter
@ToString
public class FavoriteRestaurant {
	private long id;
	private Member member;
	private Restaurant restaurant;
	
	public FavoriteRestaurant() {
		
	}
	
	public FavoriteRestaurant(Member member, Restaurant restaurant) {
		super();
		this.member = member;
		this.restaurant = restaurant;
	}
	
	public FavoriteRestaurant(long id, Member member, Restaurant restaurant) {
		super();
		this.id = id;
		this.member = member;
		this.restaurant = restaurant;
	}
	
}
