package com.edu.springboot.jsontype4;

import lombok.AllArgsConstructor;
import lombok.Data;

/*
@Data : 멤버변수 접근을 위한 getter, setter, 기본생성자 및
	Object에서 제공하는 toString()과 같은 메서드가 자동으로 
	생성된다. 
@AllArgsConstructor : 인수를 가진 생성자를 만들고 싶을때 
	추가할 수 있는 어노테이션이다. 
*/
@Data
@AllArgsConstructor
public class PersonVO {
	private String name;
	private int age;
	private String job;
}
