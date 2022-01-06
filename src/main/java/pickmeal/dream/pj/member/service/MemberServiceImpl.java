package pickmeal.dream.pj.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pickmeal.dream.pj.member.dao.MemberDao;
import pickmeal.dream.pj.member.domain.Member;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao md;

	@Override
	public void addMember(Member member) {
		md.addMember(member);
		
	}

}
