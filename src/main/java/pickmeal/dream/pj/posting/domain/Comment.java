package pickmeal.dream.pj.posting.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pickmeal.dream.pj.member.domain.Member;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Comment {
	private long id;
	private Posting posting;
	private Member member;
	private String content;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss", timezone="Asia/Seoul")
	private Date regDate;
}
