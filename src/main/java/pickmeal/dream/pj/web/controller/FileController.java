package pickmeal.dream.pj.web.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;

/**
 * 
 * 파일 첨부기능
 * 		- 외부파일에 사용자가 첨부한 파일을 저장해준다.
 * 		- 임의의 경로와, 파일이름을 만들어준다.
 * @author 윤효심
 *
 */
@Controller
public class FileController {

	@Resource(name="uploadPath")
	String uploadPath;
	
	
}
