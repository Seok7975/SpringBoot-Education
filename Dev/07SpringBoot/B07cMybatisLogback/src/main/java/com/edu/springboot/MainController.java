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
		
		/*
		회원목록에서 검색어를 입력한 후 전송한 폼값은 DTO객체를 통해 한꺼번에
		받아 저장한다. 
		 */
		System.out.println(memberDTO.getSearchField());
		System.out.println(memberDTO.getSearchKeyword());
		
//		model.addAttribute("memberList", dao.select());		
		model.addAttribute("memberList", dao.select(memberDTO));
		return "list";       
	}
	
	//회원등록
	@RequestMapping(value="/regist.do", method=RequestMethod.GET)
	public String member1() {				 
		return "regist";       
	}
	@RequestMapping(value="/regist.do", method=RequestMethod.POST)
	public String member6(HttpServletRequest req) {
		/*
		회원등록 폼에서 전송한 파라미터를 request 내장객체를 통해 저장한다. 
		개별적으로 전달받은 파라미터를 insert() 메서드로 전달한다. 
		 */
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
		//폼값을 request 내장객체로 받은 후 전달한다. 
		memberDTO = dao.selectOne(req.getParameter("id"));
		model.addAttribute("dto", memberDTO);
		return "edit";       
	}
	//수정처리 
	@RequestMapping(value="/edit.do", method=RequestMethod.POST)
	public String member7(HttpServletRequest req) {
		
		//폼값을 request 내장객체를 통해 받는다. 
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");
		String name = req.getParameter("name");
		
		//폼값을 한꺼번에 DAO로 전달하기 위해 Map을 선언한다. 
		Map<String, String> paramMap = new HashMap<String, String>();
		//파라미터를 각 Key에 저장한다. 
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

 