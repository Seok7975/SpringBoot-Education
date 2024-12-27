package com.edu.springboot.auth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        
        http.formLogin((formLogin) -> formLogin
        		.loginPage("/myLogin.do") 		// default : /login
    	        .loginProcessingUrl("/myLoginAction.do")
    	        //.failureUrl("/myError.do") 		// default : /login?error
    	        .failureHandler(myAuthFailureHandler)
    	        .usernameParameter("my_id")		// default : username
    	        .passwordParameter("my_pass") 	// default : password
        		.permitAll());
        http.logout((logout) -> logout
        		.logoutUrl("/myLogout.do") 		// default : /logout
    	        .logoutSuccessUrl("/")
        		.permitAll());        
        http.exceptionHandling((expHandling) -> expHandling
        		.accessDeniedPage("/denied.do"));
        
        return http.build();
    }
  
    /* 2단계에서 인메모리 방식으로 사용했던 인증정보는 이제 JDBC를 
    사용하므로 2개의 메서드 삭제 후 아래 메서드를 추가한다. */
    
    /* DB연결을 위한 데이터소스를 자동주입한다. 해당 설정은 프로퍼티 파일
    에 있다. */
    @Autowired
    private DataSource dataSource;
    
    /* 아래 2개의 쿼리문 실행을 통해 사용자의 인증정보와 권한을 인출한다.
    시큐리티는 이를 통해 로그인 처리를 하게된다. */
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) 
    		throws Exception {
    	auth.jdbcAuthentication()
    		.dataSource(dataSource)
    		.usersByUsernameQuery("select user_id, user_pw, enabled "
					+ " from security_admin where user_id = ?")
    		.authoritiesByUsernameQuery("select user_id, authority "
					+ " from security_admin where user_id = ?")
    		.passwordEncoder(new BCryptPasswordEncoder());
    }
}
