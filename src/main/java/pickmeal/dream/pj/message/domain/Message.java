package pickmeal.dream.pj.message.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Message {
	private long id;
	private char type; //messageType
	private String content; 
	
	public Message() {}
	
	public Message(long id, char type, String content) {
		this.id = id;
		this.type = type;
		this.content = content;
	}
}
