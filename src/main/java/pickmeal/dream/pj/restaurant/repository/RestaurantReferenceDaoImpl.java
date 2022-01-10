package pickmeal.dream.pj.restaurant.repository;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import pickmeal.dream.pj.restaurant.domain.RestaurantReference;

@Component("RestaurantReferenceDao")
public class RestaurantReferenceDaoImpl implements RestaurantReferenceDao{

	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jt;

	@Override
	public RestaurantReference getRestaurantReference(long restaurantId) {
		String sql = "SELECT restaurantId, "
				+" COUNT(CASE WHEN gender='F' THEN 1 ELSE NULL END) AS femaleCount,"
				+" COUNT(CASE WHEN gender='M' THEN 1 ELSE NULL END) AS maleCount,"
				+" COUNT(CASE WHEN age>=10 AND age <20 THEN 1 ELSE NULL END) AS 10s,"
				+" COUNT(CASE WHEN age>=20 AND age <30 THEN 1 ELSE NULL END) AS 20s,"
				+" COUNT(CASE WHEN age>=30 AND age <40 THEN 1 ELSE NULL END) AS 30s,"
				+" COUNT(CASE WHEN age>=40 AND age <50 THEN 1 ELSE NULL END) AS 40s,"
				+" COUNT(CASE WHEN age>=50 AND age <60 THEN 1 ELSE NULL END) AS 50s,"
				+" COUNT(CASE WHEN age>=60 AND age <70 THEN 1 ELSE NULL END) AS 60s"
				+" FROM RestaurantPreference"
				+" GROUP BY restaurantId HAVING restaurantId=? ";

		return jt.queryForObject(sql, new RestaurantReferenceRowMapper(), restaurantId);
	}
	
	
	
}
