package pickmeal.dream.pj.web.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	
	public List<String> saveImgToExternal(String boardName, List<MultipartFile>files, long memberId) throws Exception;
}
