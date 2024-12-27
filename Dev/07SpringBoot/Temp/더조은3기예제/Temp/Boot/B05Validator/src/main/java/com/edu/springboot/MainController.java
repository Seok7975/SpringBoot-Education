package com.edu.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	//쓰기페이지에 대한 매핑 
	@RequestMapping("/write.do")
	public String insert1() {	
		return "write";       
	}
	
	//Validator 인터페이스를 통한 폼값의 유효성 검증 
	/*
	Spring boot에서 사용하는 커맨드객체는 전송된 폼값을 한꺼번에 받아 Model객체에
	저장해준다. 만약 저장되는 속성명을 변경하고 싶다면 @ModelAttribute 어노테이션
	을 사용하면 된다. 이 경우 boardDTO가 아닌 dto라는 이름으로 저장된다. 
	*/
	@RequestMapping("/writeAction1.do")
	public String writeAction1(
			@ModelAttribute("dto") BoardDTO boardDTO, 
			BindingResult result) {
		
		//폼값검증에 성공한 경우 포워드 할 View의 경로 설정 
		String page = "result";
		System.out.println(boardDTO);
		
		//폼값 검증을 위한 인스턴스 생성 
		BoardValidator validator = new BoardValidator();
		/* 폼값을 저장한 DTO 및 검증결과 전달을 위한 객체를 인수로 전달한다.
		여기서 validate()를 호출하면 supports() 메서드가 먼저 호출되어 DTO객체가
		폼값 검증에 적합한 커맨드객체인지 검사한다. */
		validator.validate(boardDTO, result);
		
		//폼값 검증에 실패한 경우 if문의 블럭 실행 
		if (result.hasErrors()) {
			//실패한 경우 재입력을 받기 위해 쓰기페이지를 포워드한다. 
			page = "write";
			/* result가 폼값 검증에 대한 모든 결과값을 가지고 있다. 따라서 아래 부분의 
			출력문을 주석처리 하더라도 여기에서 검증 결과가 출력된다. */
			System.out.println("검증 실패 반환값1:"+
					result.toString());
			
			//제목 검증에 실패한 경우 우리가 생성한 에러코드를 출력한다.
			if(result.getFieldError("title")!=null) {
//				System.out.println("제목 검증1(에러코드):"
//					+result.getFieldError("title").getCode());
			}
			
			//내용 검증에 실패한 경우 디폴트 메세지를 출력한다. 
			if(result.getFieldError("content")!=null) {
//				System.out.println("내용 검증1(디폴트메세지):"
//					+result.getFieldError("content")
//						.getDefaultMessage());
			}
			// 이 부분은 본인의 업무에 맞게 수정할 수 있다. 
		}
	    
		return page;       
	}
	
	/* 폼값이 전송되면 BoardVO 객체를 통해 한꺼번에 받는다. 해당 객체에는
	여러가지 어노테이션을 통해 폼값 검증을 위한 로직이 추가되어 있다. 
	컨트롤러에서는 해당 객체가 직접 폼값 검증을 하겠다는 의미로 @Validated
	어노테이션을 붙여준다. */
	@RequestMapping("/writeAction2.do")
	public String writeAction2(
			@ModelAttribute("dto") @Validated BoardVO boardVO, 
			BindingResult result) {
		String page = "result";
		System.out.println(boardVO);
		
		//검증을 위한 클래스를 별도로 정의할 필요가 없으므로 주석처리한다. 
//		BoardValidator validator = new BoardValidator();
//		validator.validate(boardDTO, result);
		
		if (result.hasErrors()) {
			page = "write";
			System.out.println("검증 실패 반환값2:"+
					result.toString());
			
			if(result.getFieldError("title")!=null) {
//				System.out.println("제목 검증2(에러코드):"+
//						result.getFieldError("title")
//							.getCode());
			}
			
			if(result.getFieldError("content")!=null) {
//				System.out.println("내용 검증2(디폴트메세지):"+
//						result.getFieldError("content")
//							.getDefaultMessage());
			}
		}
		
		return page;       
	}
}




