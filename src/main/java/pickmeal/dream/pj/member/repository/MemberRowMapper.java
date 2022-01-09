package pickmeal.dream.pj.member.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import pickmeal.dream.pj.member.domain.Member;

/**
 * 모든 칼럼 정보를 담은 메뉴 객체를 뽑을 때 사용
 * @author 김보령
 *
 */
public class MemberRowMapper implements RowMapper<Member> {

	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		Member member = new Member();
		
		member.setId(rs.getLong("id"));
		member.setMemberType(rs.getString("memberType").charAt(0));
		member.setEmail(rs.getString("email"));
		member.setPasswd(rs.getString("passwd"));
		member.setNickName(rs.getString("nickName"));
		member.setBirth(rs.getString("birth"));
		member.setGender(rs.getString("gender").charAt(0));
		member.setProfileImgPath(rs.getString("profileImgPath"));
		member.setRegDate(rs.getDate("regDate"));
		
		return member;
	}
	
}
