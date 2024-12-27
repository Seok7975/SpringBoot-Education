package com.edu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

@SpringBootApplication
public class B10cJDBCLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(B10cJDBCLoginApplication.class, args);
		
		String passwd = 
			PasswordEncoderFactories.createDelegatingPasswordEncoder()
				.encode("1234");
		System.out.println(passwd);
/*
{bcrypt}$2a$10$3K3r0cbaHhalEkmQlDhZQuYmpFlEUiu181jACcEOkUTTdfX56dgw.
{bcrypt}$2a$10$x.SAKDmjg/kWHdzGNCeB8ec4MnKgy0Yj2cghT.Srj3GakAGHS6EA6
*/
	}

}
