package pickmeal.dream.pj.coupon.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import pickmeal.dream.pj.coupon.domain.Coupon;
import pickmeal.dream.pj.coupon.domain.CouponCategory;
import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.member.repository.MemberDao;
import pickmeal.dream.pj.restaurant.domain.Restaurant;
import pickmeal.dream.pj.restaurant.repository.RestaurantDao;

public class CouponRowMapper implements RowMapper<Coupon>{
	
	@Override
	public Coupon mapRow(ResultSet rs, int rowNum) throws SQLException {
		Coupon coupon = new Coupon();
		//Member member = new Member();
		//CouponCategory couponCategory = new CouponCategory();
		//Restaurant restaurant = new Restaurant();
		
		coupon.setId(rs.getLong("id"));
		
		coupon.setMember(new Member(rs.getLong("memberId")));
		
		//CouponCategory couponCategory = cd.findCouponCategoryByid(rs.getLong("couponId"));
		coupon.setCouponCategory(new CouponCategory(rs.getLong("couponId")));
		
		//restaurant = rd.findRestaurantById(rs.getLong("restaurantId"));
		coupon.setRestaurant(new Restaurant(rs.getLong("restaurantId")));
		
		coupon.setCouponNumber(rs.getString("couponNumber"));
		coupon.setUsed(rs.getBoolean("used"));
		coupon.setRegDate(rs.getDate("regDate"));
		
		return coupon;
	}

}
