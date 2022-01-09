package pickmeal.dream.pj.member.service;

import java.util.List;

import static pickmeal.dream.pj.web.constant.Constants.*;
import static pickmeal.dream.pj.web.constant.SavingPointConstants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.java.Log;
import pickmeal.dream.pj.member.dao.MemberDao;
import pickmeal.dream.pj.member.domain.Member;

@Service("memberService")
@Log
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao md;

	@Override
	@Transactional
	public Member addMember(Member member) {
		// 사용자를 추가하고
		md.addMember(member);
		// 추가한 사용자의 정보를 찾아온다.
		Member m = findLastAddMember();
		// 사용자에 대한 식력 포인트 / 신뢰 온도 / 출석률을 체크해준다.
		// 다만 식력 포인트는 0으로
		m.saveFoodPowerPoint(SIGN_UP);
		// 신뢰 온도는 36.5도로 항상 처음에 셋팅해준다.
		m.addMannerTemperature(SIGN_UP_MANNER);
		return m;
	}

	@Override
	public Member findMemberByMemberEmail(String email) {
		md.findMemberByMemberEmail(email);
		return null;
	}

	@Override
	public List<Member> findAllMembers() {
		md.findAllMembers();
		return null;
	}

	@Override
	public boolean isMember(String email) {
		return md.isMember(email);
	}

	@Override
	public Member updateMember(Member member) {
		md.updateMember(member);
		return null;
	}

	@Override
	public boolean deleteMember(long id) {
		md.deleteMember(id);
		return false;
	}

	@Override
	public Member findLastAddMember() {
		return md.findLastAddMember();
	}

}
