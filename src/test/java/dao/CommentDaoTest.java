package dao;

import static pickmeal.dream.pj.web.constant.SavingPointConstants.SIGN_UP;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.java.Log;
import pickmeal.dream.pj.config.DataSourceConfig;
import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.member.repository.MemberDao;
import pickmeal.dream.pj.member.service.MemberService;


@SpringJUnitConfig(classes= {DataSourceConfig.class})
@Log
public class CommentDaoTest {
	@Autowired
	private MemberDao md;
	
	@Autowired
	private MemberService ms;
	
	@Test
	@Transactional
//	@Commit
	public void addComment() {
		Member m = new Member();
		m.setMemberType('M');
		m.setEmail("kimkim@naver.com");
		m.setPasswd("12341234");
		m.setNickName("구리1누나");
		m.setBirth("19941108");
		m.setGender('F');
		m.setProfileImgPath("imgPath");
		
		ms.addMember(m);
		
		log.info("김구리 completed");
	}
	
//	@Test
	@Transactional
//	@Commit
	public void findLastAddComment() {
		Member m = md.findLastAddMember();
		log.info(m.toString());
	}
	
//	@Test
	@Transactional
	@Commit
	public void updateComment() {
		log.info(SIGN_UP.getKor());
		log.info(String.valueOf(SIGN_UP.getPoint()));
	}
	
//	@Test
	@Transactional
	public void deleteComment() {
		List<Member> members = md.findAllMembers();
		for (Member m : members) {
			log.info(m.toString());
		}
	}
	
//	@Test
	@Transactional
	@Commit
	public void findAllCommentByMemberId() {
		Member m = md.findMemberById(1);
		
		m.setEmail("상혁@naver.com");
		
		md.updateMember(m);
		
		log.info(md.findMemberById(1).toString());
	}
	
//	@Test
	@Transactional
//	@Commit
	public void findAllCommentByPostId() {
		md.deleteMember(4);
	}
}
