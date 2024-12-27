package com.edu.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.edu.springboot.jdbc.IMemberService;
import com.edu.springboot.jdbc.MemberDTO;
 
@Controller
public class MainController {
	
	/* DAO 클래스의 부모로 선언된 인터페이스를 통해 dao인스턴스를 생성한다. 
	상속구조를 가지고 있으므로 부모를 통해 자식에서 오버라이딩 한 메서드를 호출
	할 수 있다. 
	인터페이스에는 @Service 어노테이션이 부착되어 컨테이너가 빈을 생성해 둔 
	상태이므로 자동주입 받을 수 있다. */
	@Autowired
	IMemberService dao;
	
	//컨텍스트 루트 경로에 대한 매핑
	@RequestMapping("/")
	public String home() {
		return "home";
	}

	//회원 목록 매핑 
//	@RequestMapping("/list.do")
//	public String member2(Model model) {
//		/* DAO클래스의 select() 메서드를 호출하여 회원목록을 List컬렉션으로
//		반환받은 후 Model에 저장하고 View로 포워드한다. */
//		model.addAttribute("memberList", dao.select());		
//		return "list";       
//	}
	//검색 기능 추가
	@RequestMapping("/list.do")
	public String member2(Model model, MemberDTO memberDTO) {
		/* DAO에 검색 기능이 추가된 추상메서드를 추가한다. */
		model.addAttribute("memberList", dao.selectSearch(memberDTO));		
		return "list";       
	}
	
	/*
	@RequestMapping 어노테이션을 통해 매핑할때 전송방식과 상관없이 설정하려면
	속성값 없이 요청명만 기술한다. 
	하지만 아래와 같이 get/post 방식으로 구분하려면 value 속성에 요청명을
	method 속성에 전송방식을 명시하면된다. 
	*/
	//회원등록 : 회원가입폼 매핑
	@RequestMapping(value="/regist.do", method=RequestMethod.GET)
	public String member1() {
		//View 경로에 대한 설정만 처리 
		return "regist";       
	}
	/* @RequestMapping은 서블릿의 수명주기 메서드 중 service()와 동일하게 
	전송방식에 상관없이 매핑을 할수있다. 조금 더 간단히 매핑하고 싶다면 
	아래와 같이 @PostMapping (혹은 @GetMapping)을 사용하면 된다. */
	//@RequestMapping(value="/regist.do", method=RequestMethod.POST)
	@PostMapping("/regist.do")
	public String member6(MemberDTO memberDTO) {
		int result = dao.insert(memberDTO);
		//반환값이 1이라면 회원정보 입력에 성공이므로 콘솔에 출력
		if(result==1) System.out.println("입력되었습니다.");
		
		/* 입력이 완료되면 list.do로 이동한다. redirect 키워드는 View에 대한 
		포워드가 아니라 이동이 된다. */
		return "redirect:list.do";       
	}
	
	//회원 정보 수정
	/* 기존의 회원정보를 가져와서 수정폼에 설정한다. */
	@RequestMapping(value="/edit.do", method=RequestMethod.GET)
	public String member3(MemberDTO memberDTO, Model model) {
		/*
		매개변수 memberDTO에는 웹브라우저에서 전송된 파라미터 id가 저장된다.
		DTO를 DAO로 전달한 후 해당 아이디에 일치하는 레코드를 얻어온다. 
		따라서 아래 memberDTO에는 회원의 전체 레코드가 저장되어 있다. 
		*/
		memberDTO = dao.selectOne(memberDTO);
		//Model객체에 저장한 후 View로 전달한다. 
		model.addAttribute("dto", memberDTO);		
		return "edit";       
	}
	//회원정보 수정 처리 
	@RequestMapping(value="/edit.do", method=RequestMethod.POST)
	public String member7(MemberDTO memberDTO) {
		//폼값은 커맨드객체로 한꺼번에 받은 후 인수로 전달한다. 
		int result = dao.update(memberDTO);
		if(result==1) System.out.println("수정되었습니다.");
		//수정이 완료되면 목록으로 리다이렉트 한다. 
		return "redirect:list.do";          
	}
	
	@RequestMapping("/delete.do")
	public String member4(MemberDTO memberDTO) {	
		int result = dao.delete(memberDTO);
		if(result==1) System.out.println("삭제되었습니다.");
		return "redirect:list.do";    
	}	
}
