/* 기본 패키지 하위에 bean1을 추가한다. 해당 클래스를 컨트롤러로 사용하려면
반드시 이와같이 정의해야한다. 만약 기본패키지가 아닌 별도의 패키지를 생성하면
컨트롤러로 사용할 수 없다. */
package com.edu.springboot.bean1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//해당 클래스를 컨트롤러로 정의한다. 
@Controller
public class Di1Controller {

	/* @ResponseBody : 컨트롤러에서 처리된 내용을 View로 전달하지 않고, 즉시
	 		출력하고 싶을때 사용하는 어노테이션이다. String을 반환하면 단순히
	 		문자열이 출력되고, Map 혹은 List로 반환하면 JSON 객체 혹은 배열형태로
	 		출력된다. */
	@RequestMapping("/di1")
    @ResponseBody
	public String home() {
		//Java설정 파일을 기반으로 스프링 컨테이너를 생성한다. 
		ApplicationContext context = 
				new AnnotationConfigApplicationContext(
						BeanConfig.class);
		
		/* 컨터이너에서 person1을 주입받는다. Object형으로 저장되므로 주입받은 후
		형변환해야 사용할 수 있다. */
		Person person1 = (Person) context.getBean("person1");
		System.out.println(person1);
		
		//두번째 인수를 통해 타입을 명시하면 별도의 형변환없이 사용할 수 있다. 
		Person person2 = context.getBean("person2", Person.class);
		System.out.println(person2);
		
		//여기서 반환되는 문자열은 웹브라우저에 즉시 출력된다. 
		return "Dependency Injection1 (의존주입1)";
	}
}


