package com.edu.springboot.cookie;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CookieConfig implements WebMvcConfigurer {
	
	private static final String[] STATIC_RESOURCES = {
			"/**/*.css", "/**/*.png", "/**/*.svg", 
			"/**/*.jpg", "/**/*.html", "/**/*.ttf", 
			"/**/*.eot", "/**/*.woff", "/**/*.otf", 
	};
	  
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(cookieInterceptor())
			.addPathPatterns("/**")
	        .excludePathPatterns("/setCookie")
	        .excludePathPatterns(STATIC_RESOURCES);
	}
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	WebMvcConfigurer.super.addResourceHandlers(registry);
    	registry.addResourceHandler("/resources/**")
	        .addResourceLocations("classpath:/static/")
	        .setCachePeriod(0); // 캐시 비활성화
    }

	@Bean
	public CookieInterceptor cookieInterceptor() {
		return new CookieInterceptor();
	}
}
