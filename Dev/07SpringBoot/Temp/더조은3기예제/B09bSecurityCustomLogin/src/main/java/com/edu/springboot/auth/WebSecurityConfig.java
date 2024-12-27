package com.edu.springboot.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import jakarta.servlet.DispatcherType;

@Configuration
public class WebSecurityConfig {
	
	@Autowired
	public MyAuthFailureHandler myAuthFailureHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) 
    		throws Exception {
        http.csrf((csrf) -> csrf.disable())
            .cors((cors) -> cors.disable())
            .authorizeHttpRequests(request -> request
                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                .requestMatchers("/").permitAll()
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/guest/**").permitAll()
                .requestMatchers("/member/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()	// 어떠한 요청이라도 인증필요
            );
        
        
        /*
        로그인 페이지에 대한 디자인 커스터마이징 설정
        loginPage : 로그인 페이지의 요청명
        loginProcessingUrl : 폼값을 전송하여 로그인 처리할 요청명
        failureUrl : 로그인에 실패한 경우 이동할 요청명
        failureHandler : 별도의 핸들러 인스턴스를 등록 후 에러처리
        usernameParameter : 아이디 입력을 위한 <input>의 name 속성값
        passwordParameter : 패스워드의 name 속성값 
         */
        http.formLogin((formLogin) -> formLogin
        		.loginPage("/myLogin.do") // default : /login
    	        .loginProcessingUrl("/myLoginAction.do")
    	        //.failureUrl("/myError.do") // default : /login?error
    	        .failureHandler(myAuthFailureHandler)
    	        .usernameParameter("my_id")	// default : username
    	        .passwordParameter("my_pass") // default : password
        		.permitAll());
        
        /*
        로그아웃에 대한 커스터마이징
        logoutUrl : 로그아웃을 위한 요청명
    	logoutSuccessUrl : 로그아웃 이후 이동할 페이지 
         */
        http.logout((logout) -> logout
        		.logoutUrl("/myLogout.do") // default : /logout
    	        .logoutSuccessUrl("/")
        		.permitAll());
        /*
        권한이 부족한 경우 이동할 위치 지정(가령 user로 로그인 했는데 admin권한이
        필요한 페이지에 접근하는 경우)
         */
        http.exceptionHandling((expHandling) -> expHandling
        		.accessDeniedPage("/denied.do"));
        
        return http.build();
    }
    
	@Bean
	public UserDetailsService users() {
		UserDetails user = User.builder()
				.username("user")
				.password(passwordEncoder().encode("1234"))
				.roles("USER")   // ROLE_USER 에서 ROLE_는 자동으로 붙는다.
				.build();
		UserDetails admin = User.builder()
				.username("admin")
				.password(passwordEncoder().encode("1234"))
				.roles("USER", "ADMIN")
				.build();

		// 메모리에 사용자 정보를 담는다.
		return new InMemoryUserDetailsManager(user, admin);
	}
	
    // passwordEncoder()
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}

