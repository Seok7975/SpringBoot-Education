package com.edu.springboot.bean1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* 스프링 프로젝트에서 설정파일의 역할을 하는 클래스로 정의하기 위해 
@Configuration 어노테이션을 부착한다. 스프링 컨테이너가 시작될때 자동으로 
스캔되어 빈(Bean)이 생성된다. */
@Configuration
public class BeanConfig {
 
	/* @Bean 어노테이션을 통해 메서드에 정의된데로 자바빈을 생성한다. 
	인스턴스 변수명에 대한 별도의 설정이 없으므로 메서드명인 person1으로 
	생성된다. 
	자바 코드로 표현하면 
	Person person1 = new Person() 과 동일하다. 
	*/
	@Bean
	public Person person1() {
		//인스턴스 생성 후 setter를 통해 초기화한다. 
		Person person = new Person();
		person.setName("성유겸");
		person.setAge(11);
		person.setNotebook(new Notebook("레노버"));
		//인스턴스를 반환한다. 그러면 스프링 컨테이너에 자바빈이 생성된다. 
		return person;
	}
	
	/* 위와 동일하게 자바빈을 생성하되 name속성을 부여했으므로 해당명인
	person2로 생성된다. */
	@Bean(name="person2")
	public Person ptemp() {
		//인수 생성자를 통해 인스턴스 생성과 동시에 초기화한다. 
		Person person = new Person("알파고", 20, new Notebook("인텔"));		
		return person;
	}
}
