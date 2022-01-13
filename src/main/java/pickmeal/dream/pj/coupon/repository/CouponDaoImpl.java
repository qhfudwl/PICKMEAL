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
		String sql = "SELECT id, couponName, couponType, limitPrice FROM CouponCategory WHERE couponType = ?";
		CouponCategory couponCategory = jt.queryForObject(sql, new CouponCategoryRowMapper(), String.valueOf(couponType));
		return couponCategory;
	}
	
	@Override
	public CouponCategory findCouponCategoryByid(long id) {
		String sql = "SELECT id, couponName,couponType,limitPrice FROM CouponCategory WHERE id = ?";
		CouponCategory couponCategory = jt.queryForObject(sql, new CouponCategoryRowMapper(), id);
		return couponCategory;
	}
	
	/**
	 * 현재 발행된 쿠폰 모두 불러오기~!!
	 */
	@Override
	public List<Coupon> findAllCoupons() {
		String sql = "SELECT id, memberId, couponId, restaurantId, couponNumber, used, regDate FROM Coupon";
		List<Coupon> coupons = jt.query(sql, new CouponRowMapper());
		return coupons;
	}
	
	/**
	 * 쿠폰 발급 시켜주기~!
	 */
	@Override
	public void addCoupon(Coupon coupon) {
		String sql = "INSERT INTO Coupon(memberId, couponId, restaurantId, couponNumber)"
				+ " VALUES(?, ?, ?, ?)";
		jt.update(sql,coupon.getMember().getId(), coupon.getCouponCategory().getId(), 
				 coupon.getRestaurant().getId(), coupon.getCouponNumber());
		
	}
	
	/**
	 * 쿠폰발급시 중복 쿠폰번호가 있으면 다시 발급 시켜주기~!
	 */
	@Override
	public boolean isCouponByCouponNumber(String couponNumber) {
		String sql = "SELECT EXISTS (SELECT id FROM Coupon WHERE couponNumber = ?)";
		
		return jt.queryForObject(sql, Boolean.class,couponNumber);
	}
	
	
	/**
	 * 쿠폰 오늘이 아니면서 사용 안된 쿠폰 모두 삭제하기!! 
	 */
	@Override
	public void findAllCouponByusedAndregDate() {
		String sql = "DELETE FROM Coupon WHERE used=false and TIMESTAMPDIFF(DAY,regDate,CURDATE()) != 0";
		jt.query(sql, new CouponRowMapper());
	}
	
	/**
	 * 나의 쿠폰 찾아오기(회원 고유 아이디로)
	 */
	@Override
	public List<Coupon> findAllCouponsByMeneberId(long memberId) {
		String sql = "SELECT id, memberId, couponId, restaurantId, couponNumber, used, regDate FROM Coupon"
				+ " WHERE memberId = ?";
		List<Coupon> coupons = jt.query(sql, new CouponRowMapper(),memberId);
		return coupons;
	}
	
	/**
	 * 나의 사용한 쿠폰 내역 찾아오기
	 */
	@Override
	public List<Coupon> findUsedCouponsBymemberId(long memberId) {
		String sql = "SELECT id, memberId, couponId, restaurantId, couponNumber, used, regDate FROM Coupon"
				+ " WHERE memberId = ? AND used = true";
		return jt.query(sql, new CouponRowMapper(), memberId);
	}
	
	/**
	 * 쿠폰 사용시 쿠폰 유즈 변경!
	 */
	@Override
	public Coupon changeUsedCouponById(long id) {
		String sql = "UPDATE Coupon SET used = true WHERE id = ?";
		Coupon coupon = jt.queryForObject(sql, new CouponRowMapper(), id);
		return coupon;
	}
	
	/**
	 * 나의 사용 안한 쿠폰 내역 찾아오기
	 */
	@Override
	public List<Coupon> findUnusedCouponsBymemberId(long memberId) {
		String sql = "SELECT id, memberId, couponId, restaurantId, couponNumber, used, regDate FROM Coupon"
				+ " WHERE memberId = ? AND used = false";
		
		return jt.query(sql, new CouponRowMapper(), memberId);
	}
	/**
	 * 쿠폰 한개 찾아오기
	 */
	@Override
	public Coupon findCouponById(long id) {
		String sql = "SELECT id, memberId, couponId, restaurantId, couponNumber, used, regDate FROM Coupon"
				+ " WHERE Id = ?";
		return jt.queryForObject(sql, new CouponRowMapper(), id);
	}

	@Override
	public Coupon findCouponByCouponNumber(String couponNumber) {
		String sql = "SELECT id, memberId, couponId, restaurantId, couponNumber, used, regDate FROM Coupon"
				+ " WHERE couponNumber = '?'";
		return jt.queryForObject(sql, new CouponRowMapper(), couponNumber);
	}


	

}
