package com.edu.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/memberRegist.do")
	public String memberRegist() {
		return "memberRegist";
	}
	
	@RequestMapping("/registProcess.do")
	public String registProcess(MemberDTO memberDTO) {
		return "registProcess";
	}
	
}







