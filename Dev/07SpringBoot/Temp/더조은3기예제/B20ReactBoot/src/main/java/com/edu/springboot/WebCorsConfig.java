package com.edu.springboot;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebCorsConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
	        .allowedOriginPatterns("*")
	        .allowedMethods("GET", "POST", "PUT", "DELETE")
	        .allowedHeaders("Authorization", "Content-Type")
	        .exposedHeaders("Custom-Header")
	        .allowCredentials(true)
	        .maxAge(3600);
	}	
	
}


/*
https://kim6394.tistory.com/273
	
이렇게 했을때 에러발생
	registry.addMapping("/**")
		.allowedOrigins("*") ==> 이 부분을 allowedOriginPatterns("*") 수정
		.allowedMethods("GET", "POST", "PUT", "DELETE")
		.allowedHeaders("Authorization", "Content-Type")
		.exposedHeaders("Custom-Header")
		.allowCredentials(true)
		.maxAge(3600);

스프링부트에서 CORS 설정시 allowCredentials(true) 와 allowedOrigins("*") 을 동시에
사용할수 없도록 업데이트 됨. 
*/
