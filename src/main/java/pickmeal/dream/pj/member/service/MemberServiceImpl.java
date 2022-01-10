package pickmeal.dream.pj.member.service;

import static pickmeal.dream.pj.web.constant.Constants.SIGN_UP_MANNER;
import static pickmeal.dream.pj.web.constant.SavingPointConstants.SIGN_UP;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.java.Log;
import pickmeal.dream.pj.member.domain.FoodPowerPointItem;
import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.member.repository.MemberDao;

@Service("memberService")
@Log
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao md;
	
	@Autowired
	private MemberAchievementService mas;

	@Override
	@Transactional
	public Member addMember(Member member) {
		// 추가 전 사용자 타입 세팅
		member.setMemberType('M');
		// 추가 전 사용자 프로필 이미지 경로 세팅
		member.setProfileImgPath("/pickmeal/resources/img/profile/nonUser.png");
		// 사용자 추가
		md.addMember(member);
		
		// 마지막으로 추가한 사용자 들고오기
		Member m = findLastAddMember();
		
		// 사용자에게 신뢰온도 세팅
		m.saveMannerTemperature(SIGN_UP_MANNER);
		
		// 사용자에게 식력포인트 세팅
		m.saveFoodPowerPoint(SIGN_UP);
		
		// 신뢰온도 테이블에 넣기
		mas.addMannerTemperature(m);
		
		// 식력포인트 내역 만들기
		FoodPowerPointItem fppi = new FoodPowerPointItem();
		fppi.setMember(m);
		fppi.setDetail(SIGN_UP);
		fppi.setPoint(SIGN_UP.getPoint());
		
		// 식력포인트 테이블에 넣기
		mas.addFoodPowerPointItem(fppi);
		
		// 해당 사용자의 현재 신뢰온도를 가져와서 사용자에게 적용
		m.setMannerTemperature(mas.findMannerTemperatureByMemberId(m.getId()));
		
		// 마지막 사용자를 가져온다.
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
	public boolean isMemberByEmail(String email) {
		return md.isMemberByEmail(email);
	}

	@Override
	public boolean isMemberByNickName(String nickName) {
		return md.isMemberByNickName(nickName);
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

	@Override
	public Member findMemberById(long id) {
		return md.findMemberById(id);
	}

}
