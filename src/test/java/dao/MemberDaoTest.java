package dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.java.Log;
import pickmeal.dream.pj.config.DataSourceConfig;
import pickmeal.dream.pj.member.dao.MemberDao;
import pickmeal.dream.pj.member.domain.Member;


@SpringJUnitConfig(classes= {DataSourceConfig.class})
@Log
public class MemberDaoTest {
	@Autowired
	private MemberDao md;
	
	@Test
	@Transactional
//	@Commit
	public void addMember() {
//		Member m = new Member();
//		m.setMemberName("김아루");
		Member m2 = new Member();
		m2.setMemberName("김구리");
//		md.addMember(m);
//		log.info("김아루 completed");
		md.addMember(m2);
		log.info("김구리 completed");
	}
}
