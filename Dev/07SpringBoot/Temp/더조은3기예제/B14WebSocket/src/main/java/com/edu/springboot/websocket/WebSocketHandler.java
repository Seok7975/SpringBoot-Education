package com.edu.springboot.websocket;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/*
자동으로 빈을 생성한다.
TextWebScketHandler 클래스를 상속받아 핸들러 클래스를 정의한다. 
이때 3개의 메서드를 오버라이딩 해야한다. 
 */
@Component
public class WebSocketHandler extends TextWebSocketHandler {
	
	/*
	채팅을 위한 접속자의 정보를 저장하기 위해 Map 형식의 컬렉션을 생성한다. 
	 */
	private static final ConcurrentHashMap<String, WebSocketSession> 
		CLIENTS = new ConcurrentHashMap<String, WebSocketSession>();
	
	/*
	클라이언트가 웹소켓 서버에 접속했을때 호출되는 메서드. 접속시 
	WebSockerSession 값이 자동으로 생성되는데, 이 값을 멤버변수인
	CLIENTS에 저장한다. Key값은 세션의 고유값을 얻어와서 입력한다. 
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) 
			throws Exception {
		System.out.println("접속:::"+ session.getId() +":::"+ session);
		CLIENTS.put(session.getId(), session);
	}
	
	/*
	웹소켓 서버에서 클라이언트가 접속을 종료했을때 호출되는 메서드. 
	Map에서 해당 session값을 제거한다. 
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, 
			CloseStatus status) 
					throws Exception {
		CLIENTS.remove(session.getId());
	}
	
	/*
	클라이언트가 웹소켓 서버로 메세지를 보내면 호출되는 메서드. Map 컬렉션에
	저장된 클라이언트의 갯수만큼 반복한다. 메세지를 보낸 사용자를 제외한 
	나머지 사용자에게 메세지를 보낸다. 
	 */
	@Override
	protected void handleTextMessage(WebSocketSession session, 
			TextMessage message) 
					throws Exception {
		//메세지를 보낸 사용자의 웹소켓 세션값을 얻어온다. 
		String id = session.getId(); 
		//Map에 저장된 클라이언트의 수만큼 반복한다. 
        CLIENTS.entrySet().forEach( arg->{
        	/*
        	동일한 아이디가 아닌경우 메세지를 전송한다. 즉 메세지를 보낸
        	사용자를 제외한 전체에게 메세지를 전송(echo)한다. 
        	 */
            if(!arg.getKey().equals(id)) {
                try {
                    arg.getValue().sendMessage(message);
                } 
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
	}
}

