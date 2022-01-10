package pickmeal.dream.pj.posting.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pickmeal.dream.pj.member.domain.Member;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
	private long id;
	private long postId;
	private Member writer;
	private String content;
	private Date regDate;
}
