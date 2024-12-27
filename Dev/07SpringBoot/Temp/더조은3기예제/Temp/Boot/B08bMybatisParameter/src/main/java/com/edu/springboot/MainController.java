package com.edu.springboot;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.edu.springboot.jdbc.IMemberService;
import com.edu.springboot.jdbc.MemberDTO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController {

	@Autowired
	IMemberService dao;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
 
	//회원목록
	@RequestMapping("/list.do")
	public String member2(Model model, MemberDTO memberDTO) {
		/* 회원목록에서 검색어를 입력한 경우 파라미터를 받기 위해 DTO객체를
		매개변수로 선언한다. */
		System.out.println(memberDTO.getSearchField());
		System.out.println(memberDTO.getSearchKeyword());
		
		//파라미터를 Mapper로 전달하여 쿼리문을 실행한다. 
		model.addAttribute("memberList", dao.select(memberDTO));		
		return "list";       
	}
	
	//회원등록
	@RequestMapping(value="/regist.do", method=RequestMethod.GET)
	public String member1() {				 
		return "regist";       
	}
	
	/*
	회원등록 폼에서 전송한 정보를 request내장객체를 통해 저장한다. 
	개별적으로 전달받은 파라미터를 insert() 메서드로 전달한다. 
	*/
	@RequestMapping(value="/regist.do", method=RequestMethod.POST)
	public String member6(HttpServletRequest req) {		
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");
		String name = req.getParameter("name");
		
		int result = dao.insert(id, pass, name);
		if(result==1) System.out.println("입력되었습니다.");
		return "redirect:list.do";       
	}
		
	//회원수정
	@RequestMapping(value="/edit.do", method=RequestMethod.GET)
	public String member3(HttpServletRequest req, MemberDTO memberDTO, 
			Model model) {		
		memberDTO = dao.selectOne(req.getParameter("id"));
		model.addAttribute("dto", memberDTO);
		return "edit";       
	}
	//수정처리 
	@RequestMapping(value="/edit.do", method=RequestMethod.POST)
	public String member7(HttpServletRequest req) {		
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");
		String name = req.getParameter("name");
		
		/* 파라미터를 Map컬렉션에 저장한 후 Mapper로 전달한다. Map은 
		Key로 저장한 후 접근할 수 있다. */
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("m_id", id);
		paramMap.put("m_pass", pass);
		paramMap.put("m_name", name);
		
		int result = dao.update(paramMap);
		if(result==1) System.out.println("수정되었습니다.");
		return "redirect:list.do";          
	}
		
	//회원삭제
	@RequestMapping("/delete.do")
	public String member4(HttpServletRequest req) {	
		String id = req.getParameter("id");
		int result = dao.delete(id);
		if(result==1) System.out.println("삭제되었습니다.");
		return "redirect:list.do";    
	}	 
}

 