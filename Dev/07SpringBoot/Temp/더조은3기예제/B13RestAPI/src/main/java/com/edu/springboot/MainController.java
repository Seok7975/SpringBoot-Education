package com.edu.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/boardList.do")
	public String BoardList(){
		return "boardList";
	}
	@GetMapping("/boardView.do")
	public String boardView(){
		return "boardView";
	}
	
	//List와 View 전환 되는 Ajax 게시판
	@GetMapping("/ajaxBoardMain.do")
	public String ajaxBoardMain(){
		return "ajaxBoardMain";
	}
	//목록 내부 불러오기
	@GetMapping("/innerListHTML.do")
	public String innerListHTML(){
		return "innerListHTML";
	}
	//목록 내부 불러오기
	@GetMapping("/innerViewHTML.do")
	public String innerViewHTML(){
		return "innerViewHTML";
	}
}
 