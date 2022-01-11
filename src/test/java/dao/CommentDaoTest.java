package dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.java.Log;
import pickmeal.dream.pj.config.DataSourceConfig;
import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.posting.domain.Comment;
import pickmeal.dream.pj.posting.domain.Posting;
import pickmeal.dream.pj.posting.repository.CommentDao;
import pickmeal.dream.pj.posting.service.CommentService;


@SpringJUnitConfig(classes= {DataSourceConfig.class})
@Log
public class CommentDaoTest {
	@Autowired
	private CommentDao cd;
	
	@Autowired
	private CommentService cs;
	
//	@Test
	@Transactional
	@Commit
	public void addComment() {
		Posting p = new Posting(1);
		p.setCategory('R');
		Posting p2 = new Posting(1);
		p2.setCategory('E');
		Comment c = new Comment();
		c.setPosting(p);
//		c.setPosting(p2);
		c.setMember(new Member(1));
		c.setContent("마자요!! 이 집 일요일에만 닫는다구 하더니 월요일에 가보니깐 안열었더라구요!!");
		cd.addComment(c);
		
	}
	
//	@Test
	@Transactional
//	@Commit
	public void findLastAddComment() {
		cd.findLastAddComment(1, 'R');
	}

	@Test
	@Transactional
	public void isCommentById() {
		Comment c = new Comment();
		Posting p = new Posting();
		p.setId(1);
		p.setCategory('R');
		c.setId(1);
		c.setPosting(p);
		log.info(String.valueOf(cd.isCommentById(c)));
	}
	
//	@Test
	@Transactional
	@Commit
	public void updateComment() {
		Posting p = new Posting(1);
		p.setCategory('R');
		Comment c = new Comment();
		c.setId(1);
		c.setPosting(p);
		c.setMember(new Member(1));
		c.setContent("마자요!! 이 집 일요일에만 닫는다구 하더니 월요일에 가보니깐 안열었더라구요!!!!!");
		cd.updateComment(c);
	}
	
//	@Test
	@Transactional
	public void deleteComment() {
		Posting p = new Posting(1);
		p.setCategory('R');
		Comment c = new Comment();
		c.setId(1);
		c.setPosting(p);
		cd.deleteComment(c);
	}
	
//	@Test
	@Transactional
//	@Commit
	public void findAllCommentByMemberId() {
		for (Comment c : cd.findAllCommentByMemberId(1, 'R')) {
			log.info(c.toString());
		}
	}
	
//	@Test
	@Transactional
//	@Commit
	public void findAllCommentByPostId() {
		for (Comment c : cd.findAllCommentByPostId(1, 'R')) {
			log.info(c.toString());
		}
	}
}
