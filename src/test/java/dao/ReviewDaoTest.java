package dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import lombok.extern.java.Log;
import pickmeal.dream.pj.config.DataSourceConfig;
import pickmeal.dream.pj.restaurant.domain.Review;
import pickmeal.dream.pj.restaurant.repository.ReviewDao;

@SpringJUnitConfig(classes= {DataSourceConfig.class})
@Log
class ReviewDaoTest {
	@Autowired
	private ReviewDao rd;

	@Test
	void test() {
		Review review = rd.getReview(1);
		log.info(review.toString());
	}

}
