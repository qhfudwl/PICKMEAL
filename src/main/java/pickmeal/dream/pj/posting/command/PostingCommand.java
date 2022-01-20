package pickmeal.dream.pj.posting.command;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pickmeal.dream.pj.member.domain.Member;
/**
 * 
 * 글쓰기폼에서 사용한다
 * @author 윤효심
 *
 */
@Getter
@Setter
@ToString
public class PostingCommand {
	private Member member;
	private String address;
	private String category;
	private String title;
	private String content;	
	private String date;
	private String time;
	
}
