package com.edu.springboot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu.springboot.jdbc.IAddressService;
import com.edu.springboot.jdbc.AddressDTO;

@Controller
public class MainController {

	//Mapper 호출을 위한 자동주입 
	@Autowired
	IAddressService dao;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
 	
	/*
	페이지로 진입했을때 시/도는 미리 인출하여 <select>로 구성해야한다. 
	따라서 별도의 조건없이 중복을 제거한 후 태그를 수정한다. 
	 */
	@RequestMapping("/dynamicAddress.do")
	public String address1(Model model) {
		model.addAttribute("sidoLists", dao.selectSido());		
		return "address";       
	}	
	
	//시/도를 선택했을때 Ajax함수를 통해 호출된다. 
	@RequestMapping("/getGugun.do")
	@ResponseBody
	public Map<String, Object> address2(AddressDTO addressDTO) {
		//구/군은 2개 이상의 데이터이므로 List로 반환된다. 		
		List<AddressDTO> gugunLists = dao.selectGugun(addressDTO);		
		Map<String, Object> maps = new HashMap<>();
		//result 키값에 배열을 저장한 형태로 반환된다. 
		maps.put("result", gugunLists);
		//JSON객체 형식으로 컨트롤러에서 즉시 출력한다. 
		return maps;       
	}
}





