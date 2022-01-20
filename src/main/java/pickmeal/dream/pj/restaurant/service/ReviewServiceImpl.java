package pickmeal.dream.pj.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pickmeal.dream.pj.restaurant.command.ReviewCommand;
import pickmeal.dream.pj.restaurant.domain.Review;
import pickmeal.dream.pj.restaurant.repository.ReviewDao;

@Service("reviewService")
public class ReviewServiceImpl implements ReviewService{

	@Autowired
	private ReviewDao rd;
	
	
	@Override
	public Review getReview(long restaurantId) {	
		return rd.getReview(restaurantId);
	}


	@Override
	public void setReview(ReviewCommand r) {
		Review review = new Review();
		rd.setReview(review);
		
	}

}
