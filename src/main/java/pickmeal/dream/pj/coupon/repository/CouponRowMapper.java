package pickmeal.dream.pj.coupon.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pickmeal.dream.pj.coupon.domain.Coupon;
import pickmeal.dream.pj.coupon.domain.CouponCategory;
import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.restaurant.domain.Restaurant;

public class CouponRowMapper implements RowMapper<Coupon>{

	@Override
	public Coupon mapRow(ResultSet rs, int rowNum) throws SQLException {
		Coupon coupon = new Coupon();
		Member member = new Member();
		CouponCategory couponCategory = new CouponCategory();
		Restaurant restaurant = new Restaurant();
		
		coupon.setId(rs.getLong("id"));
		member.setId(rs.getLong("memberId"));
		couponCategory.setId(rs.getLong("couponId"));
		restaurant.setId(rs.getLong("restaurantId"));
		coupon.setCouponNumber(rs.getString("couponNumber"));
		coupon.setUsed(rs.getBoolean("used"));
		coupon.setRegDate(rs.getDate("regDate"));
		
		return coupon;
	}

}
