package pickmeal.dream.pj.restaurant.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pickmeal.dream.pj.restaurant.command.ReviewCommand;
import pickmeal.dream.pj.restaurant.domain.Review;
import pickmeal.dream.pj.restaurant.domain.ReviewItem;
import pickmeal.dream.pj.restaurant.repository.ReviewDao;
import pickmeal.dream.pj.restaurant.repository.VisitedRestaurantDao;

@Service("reviewService")
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	private ReviewDao rd;
	
	@Override
	public Review getReview(long restaurantId) {	
		return rd.getReview(restaurantId);
	}

	@Override
	public void setReview(ReviewCommand rc) {
		List<ReviewItem> itemlist = new ArrayList<ReviewItem>();
		if(rd.isReviewByRestaurantId(rc.getRestaurantId())){
			Review r = rd.getReview(rc.getRestaurantId());
			itemlist = r.getReviewItem();
			if(rc.getBathroom()==1) {
				int i = itemlist.get(0).getReviewCount();
				i++;
				itemlist.get(0).setReviewCount(i);
			}
			if(rc.getKind()==1) {
				int i = itemlist.get(1).getReviewCount();
				i++;
				itemlist.get(1).setReviewCount(i);
			}
			if(rc.getSpecialDay() ==1) {
				int i = itemlist.get(2).getReviewCount();
				i++;
				itemlist.get(2).setReviewCount(i);
			}
			if(rc.getClean() ==1) {
				int i = itemlist.get(3).getReviewCount();
				i++;
				itemlist.get(3).setReviewCount(i);
			}
			if(rc.getParking()==1) {
				int i = itemlist.get(4).getReviewCount();
				i++;
				itemlist.get(4).setReviewCount(i);
			}
			if(rc.getGoodgroup()==1) {
				int i = itemlist.get(5).getReviewCount();
				i++;
				itemlist.get(5).setReviewCount(i);
			}
			if(rc.getAlone()==1) {
				int i = itemlist.get(6).getReviewCount();
				i++;
				itemlist.get(6).setReviewCount(i);
			}
			if(rc.getBig()==1) {
				int i = itemlist.get(7).getReviewCount();
				i++;
				itemlist.get(7).setReviewCount(i);
			}
			if(rc.getInterior()==1) {
				int i = itemlist.get(8).getReviewCount();
				i++;
				itemlist.get(8).setReviewCount(i);
			}
			int userCount = r.getUserCount();
			userCount++;
			r.setReviewItem(itemlist);
			r.setUserCount(userCount);
			rd.setReview(r);
			System.out.println("원래 있는리뷰 추가 한거에요 : "+r);
		}else {
			Review r = new Review();
			r.setRestaurantId(rc.getRestaurantId());
			
			int a = rc.getBathroom();
			itemlist.add(new ReviewItem(a));
			
			int b = rc.getKind();
			itemlist.add(new ReviewItem(b));
			
			int c = rc.getSpecialDay();
			itemlist.add(new ReviewItem(c));
			
			int d = rc.getClean();
			itemlist.add(new ReviewItem(d));
			
			int e = rc.getParking();
			itemlist.add(new ReviewItem(e));
			
			int f = rc.getGoodgroup();
			itemlist.add(new ReviewItem(f));
			
			int g = rc.getAlone();
			itemlist.add(new ReviewItem(g));
			
			int h = rc.getBig();
			itemlist.add(new ReviewItem(h));
			
			int i = rc.getInterior();
			itemlist.add(new ReviewItem(i));
			
			r.setReviewItem(itemlist);
			
			r.setUserCount(1);
			
			System.out.println("새로 만든 리뷰에요 : "+ r);
			
			rd.addReview(r);
			
		}
		
	}

}
