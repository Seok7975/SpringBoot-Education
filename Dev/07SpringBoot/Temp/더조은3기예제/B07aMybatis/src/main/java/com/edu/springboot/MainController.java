package com.edu.springboot;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.springboot.jdbc.IMemberService;
import com.edu.springboot.jdbc.MemberDTO;

@Controller
public class MainController {

	/*
	서비스 인터페이스를 통해 Mapper의 메서드를 호출하므로 여기서 자동주입 받는다. 
	해당 인터페이스는 @Mapper 어노테이션이 부착되어 있으므로 컨터이너가 시작될때
	자동으로 빈이 생성된다. 
	 */
	@Autowired
	IMemberService dao;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
 
	//회원목록 
	@RequestMapping("/list.do")
	public String member2(Model model) {
		//Mapper에서 반환해준 List<MemberDTO>를 Model에 저장한다. 
		model.addAttribute("memberList", dao.select());		
		//View로 포워드한다. 
		return "list";       
	}
	
	//회원등록 : 가입폼은 get방식 매핑, 등록처리는 post방식 매핑 
	//@RequestMapping(value="/regist.do", method=RequestMethod.GET)
	@GetMapping("/regist.do")
	public String member1() {				 
		return "regist";       
	}
	//@RequestMapping(value="/regist.do", method=RequestMethod.POST)
	@PostMapping("/regist.do")
	public String member6(MemberDTO memberDTO) {
		//회원등록폼에서 전송한 모든 폼값은 DTO로 한꺼번에 받은후 Mapper로 전달한다.
		int result = dao.insert(memberDTO);
		//Mapper에서 반환된 결과값을 통해 성공여부를 알 수 있다. 
		if(result==1) System.out.println("입력되었습니다.");
		//redirect: 가 있으면 포워드가 아닌 페이지이동이 된다. 
		return "redirect:list.do";       
	}
	
	//회원정보수정1 : 기존 회원정보를 가져와서 수정폼 구성 
	@RequestMapping(value="/edit.do", method=RequestMethod.GET)
	public String member3(MemberDTO memberDTO, Model model) {		
		memberDTO = dao.selectOne(memberDTO);
		model.addAttribute("dto", memberDTO);		
		return "edit";       
	}
	//회원정보수정2 : 수정된 내용을 통해 레코드 업데이트 처리 
	@RequestMapping(value="/edit.do", method=RequestMethod.POST)
	public String member7(MemberDTO memberDTO) {		
		int result = dao.update(memberDTO);
		if(result==1) System.out.println("수정되었습니다.");
		return "redirect:list.do";          
	}
	
	//동기화 방식의 처리 
//	@RequestMapping("/delete.do")
//	public String member4(MemberDTO memberDTO) {
//		int result = dao.delete(memberDTO);
//		if(result==1) System.out.println("삭제되었습니다.");
//		return "redirect:list.do";    
//	}
	
	@RequestMapping("/delete.do")
	@ResponseBody
	public Map<String, String> member4(MemberDTO memberDTO) {
		int result = dao.delete(memberDTO);
		Map<String, String> map = new HashMap<>();
		if(result==1) { 
			System.out.println("삭제되었습니다.");
			map.put("result", "success");
		}
		else {
			System.out.println("삭제되었습니다.");
			map.put("result", "fail");
		}
			
		return map;
	}
}
