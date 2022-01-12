package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import lombok.extern.java.Log;
import pickmeal.dream.pj.config.DataSourceConfig;
import pickmeal.dream.pj.message.domain.Message;
import pickmeal.dream.pj.message.repository.MessageDao;

@SpringJUnitConfig(classes= {DataSourceConfig.class})
@Log
class MessageDaoTest {
	
	@Autowired
	private MessageDao md;
	@Test
	void test() {
		List<Message> mList = md.getMessageByType('F');
	}

}
