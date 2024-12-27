package com.edu.springboot;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.springboot.jpa.Member;
import com.edu.springboot.jpa.MemberService;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String main() {
		return "main";
	}
	
	@Autowired
    MemberService memberService;
	
	//레코드 입력
    @GetMapping("/insert.do")
    public String insert(Member member, Model model) {
   		memberService.insert();   		
   		model.addAttribute("title", "Insert");
   		model.addAttribute("result", "레코드 입력 성공");
   		model.addAttribute("mode", 0);//0:메세지만, 1:단일행, 2:복수행
   		return "total_view";
    }
    //전체 조회
    @GetMapping("/selectAll.do")
    public String selectAll(Model model) {
        List<Member> result = memberService.selectAll();
        model.addAttribute("title", "Select All");
   		model.addAttribute("result", result);
   		model.addAttribute("mode", 2);//0:메세지만, 1:단일행, 2:복수행
   		return "total_view";
    }
    //아이디로 검색
    @GetMapping("/selectById.do")
    public String selectById(@RequestParam("id") Long search, 
    		Model model) {
        Optional<Member> result = memberService.selectId(search);
        model.addAttribute("title", "Select By Id");
   		model.addAttribute("result", result.get());
   		model.addAttribute("mode", 1);//0:메세지만, 1:단일행, 2:복수행
   		return "total_view";
    }
    //이름으로 검색
    @GetMapping("/selectByName.do")
    public String selectByName(@RequestParam("name") String search, 
    		Model model) {
    	Optional<Member> result = memberService.selectName(search);
    	model.addAttribute("title", "Select By Name");
   		model.addAttribute("result", result.get());
   		model.addAttribute("mode", 1);//0:메세지만, 1:단일행, 2:복수행
   		return "total_view";
    }
    //이메일로 검색
    @GetMapping("/selectByEmail.do")
    public String selectByEmail(@RequestParam("email") String search, 
    		Model model) {
    	Optional<Member> result = memberService.selectEmail(search);
    	model.addAttribute("title", "Select By Email");
   		model.addAttribute("result", result.get());
   		model.addAttribute("mode", 1);//0:메세지만, 1:단일행, 2:복수행
   		return "total_view";
    }
    //이름으로 like 검색
    @GetMapping("/selectByNameLike.do")
    public String selectByNameLike(
    		@RequestParam("name") String search, Model model) {
    	String name = search + "%";
    	List<Member> result = memberService.selectNameLike(name);
    	model.addAttribute("title", "Select By Like Name");
   		model.addAttribute("result", result);
   		model.addAttribute("mode", 2);//0:메세지만, 1:단일행, 2:복수행
   		return "total_view";
    }
    //이름으로 like 검색 후 정렬
    @GetMapping("/selectByNameLikeNameDesc.do")
    public String selectByNameLikeNameDesc(
    		@RequestParam("name") String search, Model model) {
    	String name = search + "%";
    	List<Member> result = memberService
    			.selectNameLikeNameDesc(name);
    	model.addAttribute("title", "Select By Like Name Desc");
   		model.addAttribute("result", result);
   		model.addAttribute("mode", 2);//0:메세지만, 1:단일행, 2:복수행
   		return "total_view";
    }
    //위와 동일지만 Sort 클래스로 정렬
    @GetMapping("/selectByNameLikeOrder.do")
    public String selectByNameLikeOrder(
    		@RequestParam("name") String search, Model model) {
    	String name = search + "%";
    	
    	//정렬로 인해 메서드명이 길어지는것을 Sort를 통해 줄일 수 있음 
    	Sort sort = Sort.by(Sort.Order.desc("name"));
//    	Sort sort = Sort.by(Sort.Order.desc("name"), 
//    			Sort.Order.asc("email"));

    	List<Member> result = memberService
    			.selectNameLike(name, sort);
    	model.addAttribute("title", "Select By "
    			+ "Like Name Desc(Sort사용)");
   		model.addAttribute("result", result);
   		model.addAttribute("mode", 2);//0:메세지만, 1:단일행, 2:복수행

   		return "total_view";
    }
}    
    











    
    
    
    

//    @GetMapping("/insert")
//    public String insert(Member member, Model model) {
//   		memberService.insert();
//   		
//        return "insert";
//    }
//
//    @GetMapping("/selectAll")
//    public String selectAll(Model model) {
//        List<Member> result = memberService.selectAll();
//   		model.addAttribute("members", result);
//
//   		return "selectAll";
//    }
//
//    @GetMapping("/selectById")
//    public String selectById(@RequestParam("id") Long search, Model model) {
//        Optional<Member> result = memberService.selectId(search);
//   		model.addAttribute("member", result.get());
//
//   		return "select_id";
//    }
//
//    @GetMapping("/selectByName")
//    public String selectByName(@RequestParam("name") String search, Model model) {
//    	Optional<Member> result = memberService.selectName(search);
//   		model.addAttribute("member", result.get());
//
//   		return "select_name";
//    }
//
//    @GetMapping("/selectByEmail")
//    public String selectByEmail(@RequestParam("email") String search, Model model) {
//    	Optional<Member> result = memberService.selectEmail(search);
//   		model.addAttribute("member", result.get());
//
//   		return "select_email";
//    }
//
//    @GetMapping("/selectByNameLike")
//    public String selectByNameLike(@RequestParam("name") String search, Model model) {
//    	String name = search + "%";
//    	List<Member> result = memberService.selectNameLike(name);
//   		model.addAttribute("members", result);
//
//   		return "select_name_list";
//    }
//
//    @GetMapping("/selectByNameLikeNameDesc")
//    public String selectByNameLikeNameDesc(@RequestParam("name") String search, Model model) {
//    	String name = search + "%";
//    	List<Member> result = memberService.selectNameLikeNameDesc(name);
//   		model.addAttribute("members", result);
//
//   		return "select_name_list";
//    }
//
//    @GetMapping("/selectByNameLikeOrder")
//    public String selectByNameLikeOrder(@RequestParam("name") String search, Model model) {
//    	String name = search + "%";
//    	Sort sort = Sort.by(Sort.Order.desc("name"));
////    	Sort sort = Sort.by(Sort.Order.desc("name"), Sort.Order.asc("email"));
//
//    	List<Member> result = memberService.selectNameLike(name, sort);
//   		model.addAttribute("members", result);
//
//   		return "select_name_list";
//    }
//}
