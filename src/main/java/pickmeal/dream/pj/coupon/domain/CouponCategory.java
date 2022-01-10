package pickmeal.dream.pj.coupon.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CouponCategory {
	private long id;
	private String couponName;
	private char counponType;
	
	public CouponCategory() {
		
	}

	public CouponCategory(long id, String couponName, char counponType) {
		this.id = id;
		this.couponName = couponName;
		this.counponType = counponType;
	}
}
