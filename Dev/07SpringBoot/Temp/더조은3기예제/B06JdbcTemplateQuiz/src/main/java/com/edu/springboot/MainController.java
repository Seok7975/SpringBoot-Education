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
		DAO에서 회원레코드를 추가한 List를 반환받은후 Model객체에 
		저장한다. 
		검색기능추가 : 목록에서 사용자가 입력한 검색어가 DTO객체에 저장되므로
			DAO로 전달해야한다. 
		 */
		model.addAttribute("memberList", dao.select(memberDTO));		
		return "list";       
	}
	
	//회원정보추가
	/*
	@RequestMapping 어노테이션을 통해 매핑할때 전송방식과 상관없이 
	설정하려면 프로퍼티 없이 요청명만 기술하면된다. 
	하지만 아래와 같이 get/post 방식으로 구분하려면 value 프로퍼티에
	요청명을, method 프로퍼티에 전송방식을 명시하면된다. 
	 */
	//@RequestMapping(value="/regist.do", method=RequestMethod.GET)
	@GetMapping("/regist.do")
	public String member1() {
		return "regist";       
	}
	/*
	스프링부트 3.x에서는 매핑시 @GetMapping/@PostMapping의 사용을 
	권장하고 있다. 
	 */
	//@RequestMapping(value="/regist.do", method=RequestMethod.POST)
	@PostMapping("/regist.do")
	public String member6(MemberDTO memberDTO) {
		//전송된 폼값은 DTO로 한꺼번에 받아서 DAO를 호출한다. 
		int result = dao.insert(memberDTO);
		//반환값이 1이라면 입력에 성공
		if(result==1) System.out.println("입력되었습니다.");
		//입력이 완료되면 list.do로 이동한다. 
		return "redirect:list.do";       
	}
	
	//회원정보수정1 : 기존 회원정보 불러오기 
	@RequestMapping(value="/edit.do", method=RequestMethod.GET)
	public String member3(MemberDTO memberDTO, Model model) {
		//아이디를 파라미터로 받아서 회원정보를 인출한다. 
		memberDTO = dao.selectOne(memberDTO);
		model.addAttribute("dto", memberDTO);		
		return "edit";       
	}
	//회원정보수정2 : 수정처리 
	@RequestMapping(value="/edit.do", method=RequestMethod.POST)
	public String member7(MemberDTO memberDTO) {
		//수정된 회원정보를 받아서 update한다. 
		int result = dao.update(memberDTO);
		if(result==1) System.out.println("수정되었습니다.");
		//수정후에는 목록으로 이동한다. 
		return "redirect:list.do";          
	}
	
	@RequestMapping("/delete.do")
	public String member4(MemberDTO memberDTO) {	
		int result = dao.delete(memberDTO);
		if(result==1) System.out.println("삭제되었습니다.");
		return "redirect:list.do";    
	}	
}
