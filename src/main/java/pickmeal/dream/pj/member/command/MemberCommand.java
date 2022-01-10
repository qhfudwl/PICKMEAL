package pickmeal.dream.pj.member.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MemberCommand {
	private long id;
	private String email;
	private String passwd;
	private String nickName;
	private String birth;
	private char gender;
}
