package pickmeal.dream.pj.member.service;

import static pickmeal.dream.pj.web.constant.Constants.*;
import static pickmeal.dream.pj.web.constant.SavingPointConstants.*;

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
		member.setProfileImgPath(LEVEL0.getImgPath());
		member.makeProfileImgPath(LEVEL0);
		
		// 사용자 추가
		md.addMember(member);
		
		// 마지막으로 추가한 사용자 들고오기
		Member m = md.findLastAddMember();
		
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
		
		// 출석에 1일로 찍는다. - 회원가입 시 바로 로그인하기 때문에
		mas.addAttendance(m);
		
		// 마지막 사용자를 반환
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
	public Member findMemberById(long id) {
		return md.findMemberById(id);
	}

	@Override
	public Member signInMember(Member member) {
		// 멤버에게 출석수 세팅
		member = mas.findAttendanceByMemberId(member);
		// 연속 출석 수 확인
		// 오늘 날짜와 비교하기 - 쿼리로 할 수 있다.
		int diffDay = mas.checkAttendance(member.getId());
		// 날짜 차이가 1로 나온다면 연속 출석 수 +1 해서 업데이트
		// 날짜 차이가 1이 아니라면 무조건 1로 다시 셋팅
		if (diffDay == 1) {
			member.setAttendence(member.getAttendence()+1);
		} else {
			member.setAttendence(0);
		}
		// 테이블에도 업데이트
		mas.updateAttendance(member);
		
		// 식력 포인트 업데이트
		// 연속 출석 수에 따른 식력 포인트 업데이트
		// 테이블에도 식력포인트 업데이트
		
		
		// 완료 후 다시 사용자 정보를 db에서 불러온다.
		
		return null;
	}

}
