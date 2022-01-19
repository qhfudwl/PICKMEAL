package pickmeal.dream.pj.posting.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import pickmeal.dream.pj.member.domain.Member;
import pickmeal.dream.pj.member.service.MemberService;
import pickmeal.dream.pj.posting.util.TextFileAux;

@Controller
@Slf4j
public class ChatController {
	
	@Autowired
	TextFileAux tfa;
	
	@Autowired
	MemberService ms;

	@GetMapping("/posting/chat")
	public String chat() {
		return "posting/chat";
	}
	
	@GetMapping("/posting/chatAlarm")
	public String chatAlarm() {
		return "incl/chat_alarm";
	}
	
	@PostMapping("/posting/goChat")
	public String goChat(@RequestParam("writer") String writer, @RequestParam("commenter") String commenter,
			HttpSession session) {
		log.info(writer);
		log.info(commenter);
		
		Member chatter = ms.findMemberByMemberEmail(writer);
		Member chatWriter = new Member();
		chatWriter.setId(chatter.getId());
		chatWriter.setEmail(chatter.getEmail());
		chatWriter.setNickName(chatter.getNickName());
		
		chatter = ms.findMemberByMemberEmail(commenter);
		Member chatCommenter = new Member();
		chatCommenter.setId(chatter.getId());
		chatCommenter.setEmail(chatter.getEmail());
		chatCommenter.setNickName(chatter.getNickName());
		
		session.setAttribute("writer", chatWriter);
		session.setAttribute("commenter", chatCommenter);

		return "posting/chat";
	}
	
	@PostMapping("/posting/uploadFile")
	@ResponseBody
	public void uploadChattingFile(@RequestParam String tagData) {
		tfa.uploadTextFile(tagData);
	}
	

	@PostMapping(value="/posting/downloadFile", produces="application/text; charset=utf8")
	public ResponseEntity<?> downloadChattingFile() {
		return ResponseEntity.ok(tfa.downloadTextFile());
	}
}
