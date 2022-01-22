package pickmeal.dream.pj.config;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
/**
 * 
 * 외부에 파일을 저장해둘 때, 
 * 각 게시판 별로 위치를 지정해준다
 * 
 * 파일 경로는 프로퍼티 파일로 관리한다
 * @author 윤효심
 *
 */
@Configuration
@ComponentScan(basePackages = "pickmeal.dream.pj")
public class FileConfig {
	
	@Autowired
	PropertiesConfiguration imgPropertyConfig;
	
	@Bean
	public String uploadPath() {
		return imgPropertyConfig.getString("file.uploadPath");
	}
	
	//공지사항 게시판 관련 파일
	@Bean
	public String uploadNoticeBoardPath() {
		return imgPropertyConfig.getString("file.uploadNoticeBoardPath");
	}
	
	//리뷰 게시판 관련 파일
	@Bean
	public String uploadReviewBoardPath() {
		return imgPropertyConfig.getString("file.uploadReviewBoardPath");
	}
	
}
