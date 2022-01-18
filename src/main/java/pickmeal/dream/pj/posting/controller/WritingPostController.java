package pickmeal.dream.pj.posting.controller;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import pickmeal.dream.pj.posting.command.WritingPostCommand;

@Controller
public class WritingPostController {
	
	@Resource(name="uploadPath")
	String uploadPath;
	
	@GetMapping("post_write")
	public ModelAndView writingPostMain(HttpServletRequest request) {
		System.out.println("this is root "+ request.getSession().getServletContext().getRealPath("/"));
		ModelAndView mav = new ModelAndView();
		mav.setViewName("posting/post_write");
		return mav;
	}
	
	@GetMapping("post_writeT")
	public ModelAndView writingPostMain2(HttpServletRequest request) {
		System.out.println("this is root "+ request.getSession().getServletContext().getRealPath("/"));
		ModelAndView mav = new ModelAndView();
		mav.setViewName("posting/writingPost_temp");
		return mav;
	}
	
	
	
	
	//ajax Test
	@PostMapping("testAjax")
	@ResponseBody
	public String testAjaxToController(@RequestParam("picFile") List<MultipartFile> picFile) {
		
		
		return "true";
		
	}
	
	
	
	@PostMapping("completeWritingPost")
	public ModelAndView completeWritingPost(@ModelAttribute WritingPostCommand wpc) {
		
		//String fileName = wpc.getFile().getOriginalFilename();
       // File target = new File(uploadPath, fileName);
        ModelAndView mav = new ModelAndView();
        /*
        //경로 생성
        //upload path에 해당 폴더가 없으면 생성해준다
        if ( ! new File(uploadPath).exists()) {
            new File(uploadPath).mkdirs();
        }
      //파일 복사
        try {
            FileCopyUtils.copy( wpc.getFile().getBytes(), target);
            mav.addObject("file",  wpc.getFile());
        } catch(Exception e) {
            e.printStackTrace();
            mav.addObject("file", "error");
        }
        */
        
        
        
        //다중파일
        
        for(MultipartFile mf : wpc.getPicFile()) {
        	String picFileName = mf.getOriginalFilename();
        	File picTartget = new File(uploadPath,picFileName);
        	
        	if ( ! new File(uploadPath).exists()) {
                new File(uploadPath).mkdirs();
            }
        	
        	 //파일 복사
            try {
                FileCopyUtils.copy( mf.getBytes(), picTartget);
                //mav.addObject("file",  wpc.getFile());
            } catch(Exception e) {
                e.printStackTrace();
                //mav.addObject("file", "error");
            }
            
        }
        
        
        
        
        
        
        
		
		mav.addObject("content", wpc.getTime());
		
		
		
		
		mav.setViewName("posting/readingPost_temp");
		return mav;
	}
	
}
