package pickmeal.dream.pj.posting.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.posting.domain.Posting;
import pickmeal.dream.pj.posting.domain.TogetherEatingPosting;
import pickmeal.dream.pj.restaurant.domain.Restaurant;

public class TogetherEatingPostingRowMapper implements RowMapper<Posting>{

	@Override
	public Posting mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		TogetherEatingPosting tep = new TogetherEatingPosting();
		tep.setId(rs.getLong("id"));
		tep.setMember(new Member(rs.getLong("memberId")));
		tep.setRestaurant(new Restaurant(rs.getLong("restaurantId")));
		tep.setCategory('E');
		tep.setTitle(rs.getString("title"));
		tep.setContent(rs.getString("content"));
		//서비스에서 해주기 
		//tep.setCommentsNumber(rs.getInt(rowNum));
		tep.setLikes(rs.getInt("likes"));
		tep.setViews(rs.getInt("views"));
		tep.setRegDate(new Date(rs.getTimestamp("regDate").getTime()));
		tep.setMealTime(new Date(rs.getTimestamp("mealTime").getTime()));
		tep.setRecruitment(rs.getBoolean("recruitment"));
		tep.setMealChk(rs.getBoolean("mealChk"));
		Posting posting = tep;
		return posting;
	}

}
