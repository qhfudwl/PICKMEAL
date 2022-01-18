package pickmeal.dream.pj.posting.command;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WritingPostCommand {
	private String time;
	private MultipartFile file;
	private List<MultipartFile> picFile;
	public WritingPostCommand() {}
}
