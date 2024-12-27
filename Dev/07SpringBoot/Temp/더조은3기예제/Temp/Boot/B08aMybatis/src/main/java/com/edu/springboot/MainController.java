package com.edu.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.edu.springboot.jdbc.IMemberService;
import com.edu.springboot.jdbc.MemberDTO;

@Controller
public class MainController {

	/* 서비스 인터페이스를 통해 Mapper의 메서드를 호출하므로 여기서 
	자동주입 받는다. 해당 인터페이스에는 @Mapper 어노테이션이 있으므로 
	컨테이너에 자동으로 빈이 생성된다. */ 
	@Autowired
	IMemberService dao;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
 
	//회원목록
	@RequestMapping("/list.do")
	public String member2(Model model) {
		//Mapper에서 반환한 List컬렉션을 Model에 저장한 후 뷰를 호출한다.
		model.addAttribute("memberList", dao.select());		
		return "list";       
	}
	
	//회원등록 : 가입폼 매핑(get방식)과 등록처리(post방식) 
	//@RequestMapping(value="/regist.do", method=RequestMethod.GET)
	@GetMapping("/regist.do")
	public String member1() {				 
		return "regist";       
	}
	//@RequestMapping(value="/regist.do", method=RequestMethod.POST)
	@PostMapping("/regist.do")
	public String member6(MemberDTO memberDTO) {
		//회원등록폼에서 전송한 모든 폼값을 DTO로 한꺼번에 받은후 Mapper호출
		int result = dao.insert(memberDTO);
		if(result==1) System.out.println("입력되었습니다.");
		//redirect: 가 있으면 포워드가 아닌 페이지이동이 된다. 
		return "redirect:list.do";       
	}
	
	//회원정보수정 - 기존 정보 불러오기
	@RequestMapping(value="/edit.do", method=RequestMethod.GET)
	public String member3(MemberDTO memberDTO, Model model) {		
		memberDTO = dao.selectOne(memberDTO);
		model.addAttribute("dto", memberDTO);		
		return "edit";       
	}
	@RequestMapping(value="/edit.do", method=RequestMethod.POST)
	public String member7(MemberDTO memberDTO) {		
		int result = dao.update(memberDTO);
		if(result==1) System.out.println("수정되었습니다.");
		return "redirect:list.do";          
	}
	
	//회원삭제 
	@RequestMapping("/delete.do")
	public String member4(MemberDTO memberDTO) {
		//삭제를 위해 전달되는 id를 DTO로 받아서 사용한다. 
		int result = dao.delete(memberDTO);
		if(result==1) System.out.println("삭제되었습니다.");
		return "redirect:list.do";    
	}	 
}
