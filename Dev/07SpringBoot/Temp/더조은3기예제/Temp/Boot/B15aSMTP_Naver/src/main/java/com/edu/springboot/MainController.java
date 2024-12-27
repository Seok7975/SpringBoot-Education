package com.edu.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@RequestMapping("/notview")
	@ResponseBody
	public String main() {
		return "View 없이 컨트롤러에서 즉시 출력";
	}
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}

	//이메일 보내기 폼 
	@GetMapping("/emailSendMain.do")
	public String emailSendMain() {
		return "emailSendMain";
	}
	
	//이메일 발송을 처리하는 클래스에 대한 자동주입 
	@Autowired
	EmailSending e;
	
	//내용 입력후 전송했을때 post방식의 폼값을 받아 실제 메일 발송
	@PostMapping("/emailSendProcess.do")
	public String emailSendProcess(InfoDTO infoDTO) {		
		e.myEmailSender(infoDTO);
		return "home";
	}
}
