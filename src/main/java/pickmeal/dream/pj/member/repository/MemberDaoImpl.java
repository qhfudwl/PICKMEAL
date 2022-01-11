package pickmeal.dream.pj.member.repository;

import java.util.List;

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
		String sql = "INSERT INTO Member(memberType, email, passwd, nickName, birth, gender, profileImgPath)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		jt.update(sql, String.valueOf(member.getMemberType()), member.getEmail(), member.getPasswd(), member.getNickName(), member.getBirth(), 
				String.valueOf(member.getGender()), member.getProfileImgPath());
	}

	@Override
	public Member findMemberByMemberEmail(String email) {
		String sql = "SELECT id, memberType, email, passwd, nickName, birth, gender, profileImgPath, regDate"
				+ " FROM Member WHERE email=?";
		
//		String sql = "SELECT m.id, m.memberType, m.email, m.passwd, m.nickName, m.birth, m.gender, m.profileImgPath, m.regDate"
//				+ ", mt.temperature FROM MannerTemperature AS mt INNER JOIN Member AS m"
//				+ " ON mt.memberId=m.id WHERE m.email=?";
		
		return jt.queryForObject(sql, new MemberRowMapper(), email);
	}

	@Override
	public List<Member> findAllMembers() {
		String sql = "SELECT id, memberType, email, passwd, nickName, birth, gender, profileImgPath, regDate"
				+ " FROM Member";
		
//		String sql = "SELECT m.id, m.memberType, m.email, m.passwd, m.nickName, m.birth, m.gender, m.profileImgPath, m.regDate"
//				+ ", mt.temperature FROM MannerTemperature AS mt INNER JOIN Member AS m"
//				+ " ON mt.memberId=m.id";
				
		return jt.query(sql, new MemberRowMapper());
	}

	@Override
	public boolean isMemberByEmail(String email) {
		String sql = "SELECT EXISTS (SELECT id from Member WHERE email=?)";
		
		return jt.queryForObject(sql, Boolean.class, email);
	}

	@Override
	public boolean isMemberByNickName(String nickName) {
		String sql = "SELECT EXISTS (SELECT id from Member WHERE nickName=?)";
		
		return jt.queryForObject(sql, Boolean.class, nickName);
	}

	@Override
	public void updateMember(Member member) {
		String sql = "UPDATE Member SET email=?, passwd=?, nickName=?, birth=?, gender=?"
				+ " WHERE id=?";
		jt.update(sql, member.getEmail(), member.getPasswd(), member.getNickName(), 
				member.getBirth(), String.valueOf(member.getGender()), member.getId());
	}

	@Override
	public void deleteMember(long id) {
		String sql = "DELETE FROM Member WHERE id=?";
		jt.update(sql, id);
	}

	/**
	 * LAST_INSERT_ID() 의 경우 같은 connection 안에서만 정상적인 결과가 나온다
	 */
	@Override
	public Member findLastAddMember() {
		String sql = "SELECT id, memberType, email, passwd, nickName, birth, gender, profileImgPath, regDate"
				+ " FROM Member WHERE id=LAST_INSERT_ID()";
		
//		String sql = "SELECT m.id, m.memberType, m.email, m.passwd, m.nickName, m.birth, m.gender, m.profileImgPath, m.regDate"
//				+ ", mt.temperature FROM MannerTemperature AS mt INNER JOIN Member AS m"
//				+ " ON mt.memberId=m.id WHERE m.id=LAST_INSERT_ID()";
		
		return jt.queryForObject(sql, new MemberRowMapper());
	}

	@Override
	public Member findMemberById(long id) {
		String sql = "SELECT id, memberType, email, passwd, nickName, birth, gender, profileImgPath, regDate"
				+ " FROM Member WHERE id=?";
		
//		String sql = "SELECT m.id, m.memberType, m.email, m.passwd, m.nickName, m.birth, m.gender, m.profileImgPath, m.regDate"
//				+ ", mt.temperature FROM MannerTemperature AS mt INNER JOIN Member AS m"
//				+ " ON mt.memberId=m.id WHERE m.id=?";
		
		return jt.queryForObject(sql, new MemberRowMapper(), id);
	}

}
