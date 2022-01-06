package pickmeal.dream.pj.member.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pickmeal.dream.pj.member.domain.Member;

@Repository("memberDao")
public class MemberDaoImpl implements MemberDao {
	
	@Autowired
	JdbcTemplate jt;

	@Override
	public void addMember(Member member) {
		String sql = "INSERT INTO Member(memberName) VALUES (?)";
		
		jt.update(sql, String.valueOf(member.getMemberName()));
	}

}
