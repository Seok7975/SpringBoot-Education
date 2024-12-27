package com.edu.springboot;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/guest/index.do")
	public String welcome1() {
		return "guest";
	}
	
	@RequestMapping("/member/index.do")
	public String welcome2() {
		return "member";
	}
	
	@RequestMapping("/admin/index.do")
	public String welcome3() {
		return "admin";
	}
	
	//커스텀 로그인에서 추가
	@RequestMapping("/myLogin.do")
	public String login1(Principal principal, Model model) {
		/*
		로그인이 되지 않은 상태로 접근했을때는 NullPointerException이 발생되므로
		반드시 예외처리를 해야한다. 인증이 완료된 후 로그인 아이디를 얻어올수 있다.	
		 */
		try {
			String user_id = principal.getName();
			model.addAttribute("user_id", user_id);
		}
		catch (Exception e) {
			System.out.println("로그인 전입니다.");
		}
		return "auth/login";
	}
	
	//로그인에 실패한 경우 출력할 페이지
	@RequestMapping("/myError.do")
	public String login2() {		
		return "auth/error";
	}

	//권한이 부족한 경우 출력할 페이지
	@RequestMapping("/denied.do")
	public String login3() {		
		return "auth/denied";
	}
}

