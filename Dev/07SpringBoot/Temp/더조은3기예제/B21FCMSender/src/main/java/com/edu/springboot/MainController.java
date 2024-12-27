package com.edu.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.springboot.firebase.FCMSender;
import com.edu.springboot.firebase.MessageDTO; 

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {
	
	@RequestMapping("/")
	public String main() {
		
		return "main";
	}
	 
	private final FCMSender sender;
	
	@PostMapping("/FCMSender.do")
	public String fcmSender(Model model, MessageDTO messageDTO) {
		String result = sender.sendNotification(messageDTO);
		model.addAttribute("result", result);
		return "main";		
	}
}



