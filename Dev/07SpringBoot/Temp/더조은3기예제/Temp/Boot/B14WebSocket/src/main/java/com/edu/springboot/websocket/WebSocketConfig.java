package com.edu.springboot.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import lombok.RequiredArgsConstructor;

/* 웹소켓 설정을 위한 자동 빈생성, 소켓활성화, 인수생성자를 위한 
어노테이션을 부착한다. */
@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {

	private final WebSocketHandler webSocketHandler;
	
	/* 웹소켓에 접속할 수 있는 URL을 생성한다. 우리는 
	ws://localhost:포트번호/myChatServer 로 접속하게된다. 웹소켓 접속시
	http가 아니라 ws를 사용해야 한다. */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/myChatServer")
        	.setAllowedOrigins("*");
        /* 접속시 허용할 도메인 혹은 IP주소를 지정한다. 우리는 학습용이므로
        모든 주소를 허용한다. 실제 서비스용으로 개발시 *는 위험할 수 있다. */
    }    
}

