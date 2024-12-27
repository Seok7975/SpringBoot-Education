package com.edu.springboot;

import org.springframework.stereotype.Controller;
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

	@RequestMapping("/chatMain.do")
	public String chatMain() {
		return "chatMain";
	}

	@RequestMapping("/chatWindow.do")
	public String chatWindow() {
		return "chatWindow";
	}
}
