package com.edu.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.springboot.dto.MemberDTO;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String home(MemberDTO memberDTO, Model model) {
		
		memberDTO.setId("nakja");
		memberDTO.setPass("1234");
		memberDTO.setName("낙짜쌤");
		memberDTO.setRegidate("1975-11-30");
		
		model.addAttribute("dto", memberDTO);
		return "home";
	}
}
