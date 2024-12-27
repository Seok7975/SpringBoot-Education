package com.edu.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	/* application.properties에 설정한 값은 @Value 어노테이션으로
	별다른 설정없이 읽어올 수 있다. 읽은 값을 멤버변수에 저장한다. */
	@Value("${test.key1}")
	private String testKey1;
	@Value("${test.key2}")
	private String testKey2;
		
	@RequestMapping("/link1")
	public String link1(Model model, 
			@Value("${test.key3}") List<String> testKey3) {
			/* 컴마로 구분하여 2개 이상의 값이 저장된 경우에는 읽은값을
			List 컬렉션에 저장할 수 있다. split()과 같은 별다른 처리는 
			필요없다. */
		System.out.println("testKey1="+ testKey1);
		System.out.println("testKey2="+ testKey2);
		System.out.println("testKey3="+ testKey3);
		
		//Model객체에 저장 후 View로 전달한다. 
		model.addAttribute("testKey1", testKey1);
		model.addAttribute("testKey2", testKey2);
		model.addAttribute("testKey3", testKey3);
		
		return "link1";
	}
	
	
	/* PropertyConfig 클래스에서 @Bean(name="myprops")로 지정했으므로 속성값을
	읽을때는 아래와 같이 작성한다. 
	만약 name속성을 부여하지 않았다면 메서드명(빈의 이름)인 
	propertiesFactoryBean으로 작성해야 한다. */
	@Value("#{myprops['my.id']}")
	private String myId;
	@Value("#{myprops['my.pass']}")
	private String myPass;
	@Value("#{myprops['my.address']}")
	private String myAddress;
	@Value("#{myprops['my.age']}")
	private String myAge;
	
	@RequestMapping("/link2")
	public String link2(Model model) {
		
		System.out.println("myId="+ myId);
		System.out.println("myPass="+ myPass);
		System.out.println("myAddress="+ myAddress);
		System.out.println("myAge="+ myAge);
		
		//읽어온 속성값을 Model 객체에 저장한 후 View로 포워드 한다.
		model.addAttribute("myId", myId);
		model.addAttribute("myPass", myPass);
		model.addAttribute("myAddress", myAddress);
		model.addAttribute("myAge", myAge);
		
		return "link2";
	}
}
