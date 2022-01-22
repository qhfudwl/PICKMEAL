package pickmeal.dream.pj.chat.command;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChatCommand {
	private long writerId;
	private long commenterId;
	private List<String> fileText;
}
