package com.edu.springboot;

import lombok.Data;

/* 롬복을 통해 멤버변수에 대한 getter, setter, 생성자 등을 일괄적으로 
정의할 수 있다. 또한 Object클래스에서 제공하는 toString()과 같은 메서드도
자동으로 오버라이딩 된다. */
@Data
public class BoardDTO {

	private int idx;
	private String userid;
	private String title;
	private String content;
	
}
