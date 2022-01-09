package pickmeal.dream.pj.member.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pickmeal.dream.pj.member.domain.FoodPowerPointItem;

public class FoodPowerPointItemRowMapper implements RowMapper<FoodPowerPointItem> {

	@Override
	public FoodPowerPointItem mapRow(ResultSet rs, int rowNum) throws SQLException {
		FoodPowerPointItem fppi = new FoodPowerPointItem();
		
		fppi.setId(rs.getLong("id"));
		fppi.setPoint(rs.getInt("point"));
		fppi.setDetail(fppi.convertToSavingPointConstants(rs.getInt("detail")));
		fppi.setRegDate(rs.getDate("regDate"));
		
		return fppi;
	}

}
