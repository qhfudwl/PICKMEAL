package pickmeal.dream.pj.member.service;

import static pickmeal.dream.pj.web.constant.SavingPointConstants.ATTENDANCE;
import static pickmeal.dream.pj.web.constant.SavingPointConstants.ATTENDANCE_15DAYS;
import static pickmeal.dream.pj.web.constant.SavingPointConstants.ATTENDANCE_30DAYS;
import static pickmeal.dream.pj.web.constant.SavingPointConstants.ATTENDANCE_7DAYS;
import static pickmeal.dream.pj.web.constant.SavingPointConstants.SIGN_UP;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.java.Log;
import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.member.repository.MemberDao;
import pickmeal.dream.pj.member.util.PasswordDecoding;
import pickmeal.dream.pj.member.util.PasswordEncoding;

@Service("memberService")
@Log
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao md;
	
	@Autowired
	private MemberAchievementService mas;
	
	@Autowired
	private PasswordEncoding pe;

	@Override
	@Transactional
	public Member addMember(Member member) {
		// 추가 전 사용자 타입 세팅
		member.setMemberType('M');
		// 회원가입 식력 포인트 적립
		member.saveFoodPowerPoint(SIGN_UP);
		// 식력 포인트에 따른 프로필 이미지 경로 잡기
		member.makeProfileImgPath();
		
		// 비밀번호 암호화
		member = pe.convertPassword(member);
		
		// 사용자 추가
		md.addMember(member);
		
		// 마지막으로 추가한 사용자 들고오기 (id를 들고 오기 위해서 갔다 온다)
		member = md.findLastAddMember();
		
		// 식력포인트 적립 / 내역 생성 / 프로필 잡기
		mas.addFoodPowerPointItem(member, SIGN_UP);
		
		// 신뢰온도 테이블에 넣기
		mas.addMannerTemperature(member);
		
		// 출석에 1일로 찍는다. - 회원가입 시 바로 로그인하기 때문에
		mas.addAttendance(member);
		
		// 사용자 정보를 셋팅해서 반환
		return mas.doSettingMemberInfo(member);
	}

	@Override
	public Member findMemberByMemberEmail(String email) {
		Member member = md.findMemberByMemberEmail(email);
		return mas.doSettingMemberInfo(member);
	}

	@Override
	public List<Member> findAllMembers() {
		return md.findAllMembers();
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
	public boolean deleteMember(Member member) {
		md.deleteMember(member.getId());
		// 해당 멤버가 있는지 확인
		if (isMemberByEmail(member.getEmail())) {
			return false; // 있으면 거짓
		}
		return true; // 없으면 참
	}

	@Override
	public Member findMemberById(long id) {
		Member member = md.findMemberById(id);
		return mas.doSettingMemberInfo(member);
	}

	@Override
	public Member signInMember(Member member) {
		// 멤버에게 출석수 세팅 (로그인 전 출석 수이다)
		member = mas.findAttendanceByMemberId(member);
		
		// 연속 출석 수 확인
		
		// 오늘 날짜와 비교하기 - 쿼리로 할 수 있다.
		int diffDay = mas.checkAttendance(member.getId());
		// 날짜 차이가 1로 나온다면 연속 출석 수 +1 해서 업데이트
		// 날짜 차이가 1이 아니라면 무조건 1로 다시 셋팅
		if (diffDay == 1) {
			member.setAttendance(member.getAttendance()+1);
		} else if (diffDay > 1) {
			member.setAttendance(1);
		}
		// 테이블에도 업데이트
		mas.updateAttendance(member);
		
		// 식력 포인트 내역 추가
		// 연속 출석 수에 따른 식력 포인트 테이블 추가 및 멤버 셋팅 / 프로필 레벨 표시
		int attd = member.getAttendance(); // 연속 출석 수
		// 날짜 차이가 0 이라면 ++하면 안된다.
		if (diffDay != 0) {
			if (attd == 1) {
				member = mas.addFoodPowerPointItem(member, ATTENDANCE);
			} else if (attd == 7) {
				member = mas.addFoodPowerPointItem(member, ATTENDANCE_7DAYS);
			} else if (attd == 15) {
				member = mas.addFoodPowerPointItem(member, ATTENDANCE_15DAYS);
			} else if (attd == 30) {
				member = mas.addFoodPowerPointItem(member, ATTENDANCE_30DAYS);
			}
		}
		
		// 완료 후 모든 값을 셋팅한 사용자 정보를 반환한다.		
		return mas.doSettingMemberInfo(member);
	}

	@Override
	public Member findLastAddMember() {
		return md.findLastAddMember();
	}

}
