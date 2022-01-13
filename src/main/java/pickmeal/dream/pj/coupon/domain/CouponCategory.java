package pickmeal.dream.pj.coupon.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CouponCategory {
	private long id;
	private String couponName;
	private char couponType;
	private String limitPrice;
	
	public CouponCategory() {
		
	}
	
	public CouponCategory(long id) {
		this.id = id;
	}

	public CouponCategory(long id, String couponName, char couponType, String limitPrice) {
		this.id = id;
		this.couponName = couponName;
		this.couponType = couponType;
		this.limitPrice = limitPrice;
	}
}
