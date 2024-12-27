package com.edu.springboot.bean2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*
이와같이 이름을 지정해서 빈을 생성할 수 있다. 즉 Computer 타입의 
macBook이라는 빈이 생성된다. 
Computer macBook = new Computer() 와 같은 의미이다. 
 */
@Component("macBook")
public class Computer {
	
	//멤버변수는 지정한 값으로 초기화 
	@Value("M1")
	private String cpu;

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	@Override
	public String toString() {
		return "Notebook [cpu=" + cpu + "]";
	}
}
