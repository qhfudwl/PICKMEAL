package service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.java.Log;
import pickmeal.dream.pj.config.DataSourceConfig;
import pickmeal.dream.pj.posting.domain.Comment;
import pickmeal.dream.pj.posting.service.CommentService;

@SpringJUnitConfig(classes= {DataSourceConfig.class})
@Log
public class CommentServiceTest {
	
	@Autowired
	CommentService cs;
	
	@Test
	@Transactional
	@Commit
	public void findAllCommentByPostId() {
		List<Comment> comments = cs.findAllCommentByPostId(1, 'E');
		
		for(Comment c : comments) {
			log.info(c.toString());
		}
		
	}
}
