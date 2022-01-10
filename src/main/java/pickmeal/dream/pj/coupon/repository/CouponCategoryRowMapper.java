package pickmeal.dream.pj.coupon.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pickmeal.dream.pj.coupon.domain.CouponCategory;

public class CouponCategoryRowMapper implements RowMapper<CouponCategory>{

	@Override
	public CouponCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
		CouponCategory couponCategory = new CouponCategory(rs.getLong("id"), rs.getString("couponName"),
				rs.getString("couponType").charAt(0));
		return couponCategory;
	}
}
