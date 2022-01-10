package pickmeal.dream.pj.coupon.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import pickmeal.dream.pj.coupon.domain.Coupon;
import pickmeal.dream.pj.coupon.domain.CouponCategory;
import pickmeal.dream.pj.restaurant.domain.Restaurant;

@Repository("couponDaoImpl")
public class CouponDaoImpl implements CouponDao{
	
	@Autowired
	JdbcTemplate jt;

	
	/**
	 * 쿠폰카테고리 발생
	 */
	@Override
	public CouponCategory generateCouponTypeByRestaurant(char couponType) {
		String sql = "SELECT id, couponName, couponType FROM CouponCategory WHERE couponType = ?";
		CouponCategory couponCategory = jt.queryForObject(sql, new CouponCategoryRowMapper(), String.valueOf(couponType));
		return couponCategory;
	}



	@Override
	public List<Coupon> findAllCoupons() {
		String sql = "SELECT id, memberId, couponId, restaurantId, couponNumber, used, regDate FROM Coupon";
		List<Coupon> coupons = jt.query(sql, new CouponRowMapper());
		return coupons;
	}



	@Override
	public void removeNotUsedByRegDate(boolean used, Date regDate) {
		String sql = "DELETE FROM Coupon WHERE used = ? Date regDate = ?";
		jt.update(sql, used, regDate);
		
	}



	@Override
	public List<Coupon> findAllCouponsByMeneberId(long memberId) {
		String sql = "SELECT id, memberId, couponId, restaurantId, couponNumber, used, regDate FROM Coupon"
				+ " WHERE memberId = ?";
		List<Coupon> coupons = jt.query(sql, new CouponRowMapper(),memberId);
		return coupons;
	}



	@Override
	public void addCoupon(Coupon coupon) {
		String sql = "INSERT INTO Coupon(memberId, couponId, restaurantId, couponNumber)"
				+ " VALUES(?, ?, ?)";
		jt.update(sql,coupon.getMember().getId(), coupon.getCouponCategory().getId(), 
				 coupon.getRestaurant().getId(), coupon.getCouponNumber());
		
	}

}
