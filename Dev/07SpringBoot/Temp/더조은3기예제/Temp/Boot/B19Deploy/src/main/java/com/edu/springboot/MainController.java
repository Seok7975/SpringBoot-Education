package com.edu.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.springboot.jdbc.ITbService;
import com.edu.springboot.jdbc.TbDTO;

@Controller
public class MainController {
	
	@Autowired
	ITbService dao;
	
	@RequestMapping("/")
	public String home(Model model) {
		List<TbDTO> rows = dao.tbList();
		System.out.println(rows);
		model.addAttribute("rows", rows);
		return "home";
	}

}
