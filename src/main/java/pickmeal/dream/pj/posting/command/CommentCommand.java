package pickmeal.dream.pj.posting.command;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommentCommand {
	private long id;
	private long postId;
	private char category;
	private long memberId;
	private String content;
	private Date regDate;
}
