package com.edu.springboot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/* 
롬복을 통해 멤버변수에 대한 getter, setter, 기본생성자 등을 일괄적으로 
정의할 수 있다. 또한 Object클래스에서 제공하는 toString()과 같은 메서드도
자동으로 오버라이딩 된다. 
*/
@Data
//인자를 가진 생성자를 추가한다. 
@AllArgsConstructor
//기본생성자를 추가한다. 
@NoArgsConstructor
/*
//getter 혹은 setter만 추가할 수 있는 어노테이션 
@Setter
@Getter
*/
public class BoardDTO {

	private int idx;
	private String userid;
	private String title;
	private String content;
	
}
