package pickmeal.dream.pj.config;

import java.io.File;

import javax.servlet.ServletContext;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
/**
 * 이미지경로 프로퍼티 파일을 읽어오는 빈을 생성
 * @author 윤효심
 *
 */
@Configuration
@ComponentScan(basePackages = "pickmeal.dream.pj")
public class CommonImgConfig {
	
	@Bean
	public PropertiesConfiguration imgPropertyConfig(ServletContext scontext) {
		PropertiesConfiguration propertyConfig;
		//scontext.getRealPath("/WEB-INF/config/imgpath.properties");
		propertyConfig = new PropertiesConfiguration();
		try {
			propertyConfig.load(new File(scontext.getRealPath("/WEB-INF/config/imgpath.properties")));
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return propertyConfig;
	}
}
