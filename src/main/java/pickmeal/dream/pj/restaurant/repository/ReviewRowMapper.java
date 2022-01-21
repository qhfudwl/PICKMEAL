package pickmeal.dream.pj.restaurant.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import pickmeal.dream.pj.restaurant.domain.Review;
import pickmeal.dream.pj.restaurant.domain.ReviewItem;
import pickmeal.dream.pj.web.constant.ReviewIconImgPath;
import pickmeal.dream.pj.web.constant.ReviewMessage;

public class ReviewRowMapper implements RowMapper<Review> {

	@Override
	public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
		Review review = new Review();
		review.setRestaurantId(rs.getLong("restaurantId"));
		review.setUserCount(rs.getInt("userCount"));
		
		//추후수정가능
		/*
		review.setBathroom(rs.getInt("bathroom"));
		review.setKind(rs.getInt("kind"));
		review.setSpecialDay(rs.getInt("specialDay"));
		review.setClean(rs.getInt("clean"));
		review.setParking(rs.getInt("parking"));
		review.setGoodgroup(rs.getInt("goodgroup"));
		review.setAlone(rs.getInt("alone"));
		review.setBig(rs.getInt("big"));
		review.setInterior(rs.getInt("interior"));
		*/
		List<ReviewItem> reviewItem = new ArrayList<>();
		
		reviewItem.add(new ReviewItem("BATHROOM",ReviewMessage.BATHROOM.toString(),ReviewIconImgPath.BATHROOM.toString(),rs.getInt("bathroom")));
		reviewItem.add(new ReviewItem("KIND",ReviewMessage.KIND.toString(),ReviewIconImgPath.KIND.toString(),rs.getInt("kind")));
		reviewItem.add(new ReviewItem("SPECIALDAY",ReviewMessage.SPECIALDAY.toString(),ReviewIconImgPath.SPECIALDAY.toString(),rs.getInt("specialDay")));
		reviewItem.add(new ReviewItem("CLEAN",ReviewMessage.CLEAN.toString(),ReviewIconImgPath.CLEAN.toString(),rs.getInt("clean")));
		reviewItem.add(new ReviewItem("PARKINIG",ReviewMessage.PARKINIG.toString(),ReviewIconImgPath.PARKINIG.toString(),rs.getInt("parking")));
		reviewItem.add(new ReviewItem("GOODGROUP",ReviewMessage.GOODGROUP.toString(),ReviewIconImgPath.GOODGROUP.toString(),rs.getInt("goodgroup")));
		reviewItem.add(new ReviewItem("ALONE",ReviewMessage.ALONE.toString(),ReviewIconImgPath.ALONE.toString(),rs.getInt("alone")));
		reviewItem.add(new ReviewItem("BIG",ReviewMessage.BIG.toString(),ReviewIconImgPath.BIG.toString(),rs.getInt("big")));
		reviewItem.add(new ReviewItem("INTERIOR",ReviewMessage.INTERIOR.toString(),ReviewIconImgPath.INTERIOR.toString(),rs.getInt("interior")));
		
		//리뷰가 많은 순으로 sorting;
		Collections.sort(reviewItem);
		review.setReviewItem(reviewItem);
		
		return review;
	} 

}
