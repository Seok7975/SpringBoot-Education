package com.edu.springboot.bean2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/* @Component 어노테이션을 통해 스프링 컨테이너 시작시 자동으로 빈이 생성된다. 
여기서는 별도로 이름을 지정하지 않았으므로 첫글자를 소문자로 바꾼 student로 
생성된다. */
@Component
public class Student {
	
	/* @Value 어노테이션에 지정된 값으로 멤버변수가 초기화된다. 이 값은
	setter를 통해 설정된다. */
	@Value("이순신")
	private String name;
	
	@Value("30")
	private int age;
	
	/* 객체타입의 멤버변수는 @Autowired 어노테이션을 통해 자동으로 빈을
	주입받는다. 이때 @Qualifier 어노테이션이 있으면 빈의 이름까지 지정해서
	주입받을 수 있다. 만약 없으면 타입으로만 빈을 찾아 주입받게된다. */
	@Autowired
	@Qualifier("macBook")
	private Computer notebook;
	
	//생성자, getter, setter, toString() 을 정의한다. 
	public Student() {}
	public Student(String name, int age, Computer notebook) {
		super();
		this.name = name;
		this.age = age;
		this.notebook = notebook;
	}	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Computer getNotebook() {
		return notebook;
	}
	public void setNotebook(Computer notebook) {
		this.notebook = notebook;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", notebook=" + notebook + "]";
	}
}
