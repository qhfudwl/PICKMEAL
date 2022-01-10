package dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import lombok.extern.java.Log;
import pickmeal.dream.pj.config.DataSourceConfig;
import pickmeal.dream.pj.restaurant.domain.RestaurantReference;
import pickmeal.dream.pj.restaurant.repository.RestaurantReferenceDao;

@SpringJUnitConfig(classes= {DataSourceConfig.class})
@Log
class RestaurantReferenceDaoTest {

	@Autowired
	private RestaurantReferenceDao rrd;
	
	@Test
	void test() {
		RestaurantReference rr = rrd.getRestaurantReference(1);
		log.info(rr.toString());
	}

}
