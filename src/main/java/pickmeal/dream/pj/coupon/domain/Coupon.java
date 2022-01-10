package pickmeal.dream.pj.coupon.domain;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.restaurant.domain.Restaurant;

@Getter
@Setter
public class Coupon {
	private long id;
	private Member member;
	private Restaurant restaurant;
	private CouponCategory couponCategory;
	private String couponNumber;
	private boolean used;
	private Date regDate;
}
