package com.edu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

@SpringBootApplication
public class B10aSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(B10aSecurityApplication.class, args);
		
		/*
		시큐리티에서 인증에 사용하는 암호화된 패스워드는 아래와 같이 생성할 수 있다. 
		실행할때마다 인코딩의 변경이 있어 랜덤한 문자열을 반환하게된다. 
		즉 암호화할때마다 문자열이 변경되지만 시큐리티는 이를 통해 인증처리를 해준다. 
		 */
//		String passwd = PasswordEncoderFactories
//				.createDelegatingPasswordEncoder().encode("1234");
//		System.out.println(passwd);
		
	}

}
