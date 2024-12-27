package com.edu.springboot.firebase;

import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FCMSender {

	private final FirebaseMessaging firebaseMessaging;
	
	public String sendNotification(MessageDTO messageDTO) {
		String result = "";
		Notification notification = Notification.builder()
				.setTitle(messageDTO.getTitle())
				.setBody(messageDTO.getBody())
				.setImage(messageDTO.getImage()).build();
		Message message = Message.builder()
				.setToken(messageDTO.getToken())
				.setNotification(notification)
				.putData("message", messageDTO.getAdd_data()).build();
		try {
			firebaseMessaging.send(message);
			result = "메세지 전송 성공";
		}
		catch (Exception e) {
			e.printStackTrace();
			result = "메세지 전송 실패";
		}
		
		return result;
	}	
}


