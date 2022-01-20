package dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import lombok.extern.java.Log;
import pickmeal.dream.pj.config.DataSourceConfig;
import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.posting.domain.Posting;
import pickmeal.dream.pj.posting.repository.PostingDao;
import pickmeal.dream.pj.restaurant.domain.Restaurant;

@SpringJUnitConfig(classes= {DataSourceConfig.class})
@Log
class PostingDaoTest {

	@Autowired
	private PostingDao pd;
	
	@Test
	void test() {
		/*Posting posting= new Posting();
		posting.setMember(new Member(1));
		posting.setRestaurant(new Restaurant(1));
		posting.setCategory('R');
		posting.setTitle("this is title");
		posting.setContent("this is content");
		posting.setLikes(10);
		posting.setViews(20);
		
		pd.addPosting(posting);*/
	}

}
