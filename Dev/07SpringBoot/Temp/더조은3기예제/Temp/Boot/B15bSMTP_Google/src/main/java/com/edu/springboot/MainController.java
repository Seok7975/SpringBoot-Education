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

	@GetMapping("/emailSendMain.do")
	public String emailSendMain() {
		return "emailSendMain";
	}

	@Autowired
	EmailSending e;
	
	@PostMapping("/emailSendProcess.do")
	public String emailSendProcess(InfoDTO infoDTO) {		
		e.myEmailSender(infoDTO);
		return "home";
	}
}





