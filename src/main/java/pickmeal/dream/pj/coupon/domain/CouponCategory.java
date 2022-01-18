package pickmeal.dream.pj.coupon.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CouponCategory {
	private long id;
	private String couponName;
	private char couponType;
	private String limitPrice;
	
	public CouponCategory() {
		
	}

	public CouponCategory(long id, String couponName, char couponType, String limitPrice) {
		this.id = id;
		this.couponName = couponName;
		this.couponType = couponType;
		this.limitPrice = limitPrice;
	}
}
