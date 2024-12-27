package com.edu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class B10cJDBCLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(B10cJDBCLoginApplication.class, args);
		
		/* 시큐리티에서 인증에 사용하는 암호화된 패스워드는 아래의 
		메서드를 통해 만들수 있다. 실행할때 마다 인코딩의 변경이 있어
		랜덤한 문자열을 반환하게된다. */
//		String passwd = 
//			PasswordEncoderFactories.createDelegatingPasswordEncoder()
//				.encode("1234");
//		System.out.println(passwd);
	}

}
