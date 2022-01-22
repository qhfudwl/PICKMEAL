package pickmeal.dream.pj.web.service;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.java.Log;
/**
 * 
 * 게시판별로 첨부파일들을 외부경로에 저장한다
 * 		1) 파일 이름 생성
 * 		2) 파일 경로 설정
 * 
 * @author 윤효심
 *
 */
@Service("fileService")
@Log
public class FileServiceImpl implements FileService{
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	@Resource(name="uploadNoticeBoardPath")
	private String uploadNoticeBoardPath;
	
	@Resource(name="uploadReviewBoardPath")
	private String uploadReviewBoardPath;

	public List<String> saveImgToExternal(String boardName, List<MultipartFile>files, long memberId) throws Exception{
		
		// 게시물 1개당 업로드한 파일들의 경로 (ex. img src에 들어갈것)
		List<String> imgSrc = new ArrayList<String>();

		//파일 경로 설정
		String imgPath;
		if(boardName.equals("NOTICE")) {
			imgPath = uploadNoticeBoardPath + "/" + memberId + "/" + getTodayDate();
		}
		else if(boardName.equals("REVIEW")) {
			imgPath = uploadReviewBoardPath + "/" + memberId + "/" + getTodayDate();
		}
		else {
			imgPath = uploadPath;
		}
		
		//파일 이름 설정
		String imgNamePart = "n" +memberId +"_"+ getTodayDateTime();

		int i = 0;
		for(MultipartFile mf : files) {
			//1. 파일 이름 만들어주기 ( 파일이름이 겹칠 수 있어서 )
			//		규칙 : 게시판이름 + 회원아이디(숫자) + 오늘날짜 + 숫자변수 + 확장자
	    	String imgName = imgNamePart+"_"+(i++)+"."+(mf.getOriginalFilename().substring(mf.getOriginalFilename().lastIndexOf(".") + 1));

	    	//2. 경로 설정 해주기 ( 규칙 : 공지사항 게시판 - 사용자이름 - 날짜 )
	    	File imgTarget = new File(imgPath,imgName);
	    	
	    	//3. 경로 없으면 만들어주기
	    	if ( ! new File(imgPath).exists()) {
	            new File(imgPath).mkdirs();
	        }
	    	
	    	//4. 파일 복사
	    	FileCopyUtils.copy( mf.getBytes(), imgTarget);
	 
	    	//5. img src 경로 만들어주기 
	    	imgSrc.add(imgTarget.toString());
	    }
		for(String imgsrc : imgSrc) {
			log.info("imgsrc?"+imgsrc);
		}

		return imgSrc;
		
		
	}
	
	public String getTodayDate() {
		// 현재 날짜 구하기
		LocalDate now = LocalDate.now();
		// 포맷 정의
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		// 포맷 적용
		String formatedNow = now.format(formatter);
		
		return formatedNow;
	}
	
	public String getTodayDateTime() {
		// 현재 날짜,시간 구하기
		LocalDateTime now = LocalDateTime.now();
		// 포맷 정의
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		// 포맷 적용
		String formatedNow = now.format(formatter);
		
		return formatedNow;
	}
	

	
}
