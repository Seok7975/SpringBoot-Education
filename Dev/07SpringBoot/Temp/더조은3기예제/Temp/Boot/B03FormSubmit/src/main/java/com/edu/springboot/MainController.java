package com.edu.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/* JSP에서는 컨트롤러로 정의하기 위해 Servlet클래스로 정의한 후 
매핑정보를 입력하였다. 하지만 스프링에서는 아래와 같이 @Controller 
어노테이션을 부착하면 자동으로 컨트롤러 클래스로 정의된다. 또한 사용을
위해 별도로 인스턴스를 생성할 필요없이 스프링 컨테이너가 시작시 자동으로
인스턴스(빈)을 생성해준다. */
@Controller
public class MainController {
	
	//home 경로 매핑
	@RequestMapping("/")
	public String home() {				
		return "home";
	}

	/* 컨트롤러에 매핑된 각 메서드에서 String을 반환하면 
	application.properties의 JSP설정에 의해 View의 경로가 조립된다. 
	"prefix경로 + 함수의반환값 + suffix경로"에 해당하는 JSP파일을 
	찾아 포워드하게된다. 즉 이 함수는 index.jsp파일을 찾아서 포워드한다.
	*/
    @RequestMapping("/index.do")
    public String index() {
        return "index";
    }     
    @RequestMapping("/sub.do")
    public String sub() {
    	/* 만약 views하위에 별도의 폴더가 필요하다면 이와 같이 슬러쉬로
    	구분하여 작성하면 된다. sub폴더 하위의 sub.jsp를 찾아 포워드
    	한다. */
        return "sub/sub";
    }
    
    /* 
    1. request 내장객체를 통해 폼값을 전송받아 사용 
    */
    /* 컨트롤러에서는 아래와 같이 요청명에 대한 매핑을 한 후 요청을 
    처리할 메서드를 정의한다. 해당 메서드는 일반적인 방식으로 호출하는게 
    아니라 요청명을 통해 자동으로 호출된다. 따라서 메서드명은 a1, a2와
    같이 네이밍해도 크게 문제가 없다. 단, 중복되지 않으면 된다. */
    @RequestMapping("/form1.do")
    public String form1(HttpServletRequest httpServletRequest, 
    		Model model) {
    	/* 이 메서드에서 요청을 처리하기 위해 request내장객체와 
    	Model객체를 사용해야 하므로, 매개변수로 미리 정의한다. 
    	컨트롤러에서 요청을 처리하기 위한 함수는 함수명으로 호출하는 
    	방식이 아니므로, 매개변수로 정의한 객체를 스프링 컨테이너가
    	자동으로 생성해주는 구조를 가지고 있다. */
    	
    	//request내장객체를 통해 폼값을 받아서 변수에 저장한다. 
    	String name = httpServletRequest.getParameter("name");
		String age = httpServletRequest.getParameter("age");

		/* View로 전달할 데이터가 있는경우 서블릿에서는 request영역에
		저장한 후 뷰로 포워드했다. 스프링에서는 Model객체에 데이터를
		저장하고, 포워드 대신 뷰의 경로를 String으로 반환한다. */
		model.addAttribute("name", name);
        model.addAttribute("age", age);

        //views하위의 폴더 및 파일의 경로를 반환한다. 
        return "form/submit1";       
    }

    /*
    2.@RequestParam 어노테이션을 통해 파라미터를 받은 후 변수에 저장
    */
    @RequestMapping("/form2.do")
    public String form2(@RequestParam("name") String name,
    	@RequestParam("age") String age, Model model) {
    	/* "요청명?name=저팔계" 이와 같이 요청을 받으면 어노테이션에 
    	지정한 이름을 통해 폼값을 받아서 우측의 변수에 값을 저장한다. 
    	이렇게 저장된 값은 함수 내부에서 Model객체에 저장된다. */    	

        model.addAttribute("name", name);
        model.addAttribute("age", age);

        return "form/submit2";       
    }

    /* 3.커맨드객체를 통해 파라미터를 한꺼번에 받아 DTO에 저장한다. */
    @RequestMapping("/form3.do")
    public String form3(PersonDTO personDTO) {    	
    	/* 요청시 사용되는 파라미터명과 DTO의 멤버변수명을 동일하게
    	정의하면 갯수에 상관없이 setter를 통해 한꺼번에 폼값을 
    	받아 저장하게된다. 또한 Model객체에 저장까지 자동으로 진행되므로
    	View로 전달하기 위해 별도로 저장할 필요가 없어진다. */
        return "form/submit3";       
    }
    
    /* 4.@PathVariable 어노테이션으로 경로형태로 전달되는 파라미터를
    순서대로 변수에 저장하는 방식이다. 단 이경우 파라미터를 경로로 
    인식하므로 정적리소스 사용시 경로설정에 주의해야한다. */
	@RequestMapping("/form4/{name}/{age}")
	public String form4(@PathVariable String name,
			@PathVariable String age, Model model) {
		
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		return "form/submit4";
	}

	///////////////////////////////////////////////////
	//퀴즈]
	//회원가입 페이지에 대한 매핑 처리(get방식)
	@RequestMapping("/memberRegist.do")
	public String memberRegist() {

		return "member/regist";
	}
	//회원가입 처리 페이지에 대한 매핑 처리(post방식)
	@RequestMapping("/registProcess.do")
	public String registProcess(QuizVO quizVO) {
		/* 회원가입 페이지에서 전송된 폼값은 QuizVo 객체를 통해 
		한번에 받아서 Model객체에 저장한 후 View로 전달한다. 
		DTO, VO와 같은 커맨드객체를 통해 폼값을 받으면 Model객체에 
		저장까지 자동으로 되므로 개발의 생산성을 높일 수 있다. */
		return "member/result";
	}
	
	//퀴즈] 로그인 폼에 대한 매핑 
	@RequestMapping("/memberLogin.do")
	public String memberLogin() {
		return "member/login";
	}
	//로그인 처리에 대한 매핑
	//방법1] 커맨드 객체를 통해 폼값을 받음
//	@RequestMapping("/loginProcess.do")
//	public String loginResult(QuizVO quizVO) {
//		/* 커맨드 객체를 통해 폼값을 한꺼번에 받아서 처리한다. 
//		커맨드 객체로 사용할 클래스명이 QuizVO라면 매개변수명은
//		반드시 클래스명과 동일한 이름으로 사용해야한다. 즉 여기서는
//		quizVO로 명시해야 한다. */
//		return "member/result2";
//	}
	//방법2] request내장객체를 통해 폼값을 받음
	@RequestMapping("/loginProcess.do")
	public String loginResult(HttpServletRequest req, Model model) {
		//request 내장객체를 통해 폼값을 받음
		String id = req.getParameter("id");
		String pass1 = req.getParameter("pass1");
		System.out.println("id="+ id);
		System.out.println("pass="+ pass1);		
		
		//VO객체에 폼값을 저장
		QuizVO quizVO = new QuizVO();
		quizVO.setId(id);
		quizVO.setPass1(pass1);
		
		//Model객체에 VO를 저장한 후 View로 전달 
		model.addAttribute("quizVO", quizVO);
		
		//View 경로 반환 
		return "member/result2";
	}
}










