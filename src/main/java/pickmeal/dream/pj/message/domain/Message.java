package pickmeal.dream.pj.message.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Message {
	private long id;
	private char type;
	private String content;
	
	public Message() {}
	
	public Message(long id, char type, String content) {
		this.id = id;
		this.type = type;
		this.content = content;
	}
}
