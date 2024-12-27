package com.edu.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.springboot.jpa.Member;
import com.edu.springboot.jpa.MemberService;

@Controller
public class MainController {
	
	@Autowired
	MemberService memberService;

	@GetMapping("/")
	public String main() {
		return "main";
	}
 
	@GetMapping("/selectByNameLike.do")
    public String selectByNameLike(@RequestParam("name") String pname,
									@RequestParam("page") String page,
									Model model) {
		System.out.println("selectMembers3(검색어) : "+ pname );
		System.out.println("selectMembers3(페이지) : "+ page );
		
		//뒤에만 %있음
		String name = pname +"%";
		//id를 내림차순 정렬
		Sort sort = Sort.by(Sort.Order.desc("id"));
		int pageNum = Integer.parseInt(page) - 1;
		
		//pageNum에 해당하는 게시물을 한페이지에 5개로 설정하고 정렬을 적용.  
		Pageable pageable = PageRequest.ofSize(5).withPage(pageNum).withSort(sort);
		//앞에서의 설정값과 검색어를 기반으로 쿼리문 실행. 반환된 결과에서 여러가지 정보를 얻어올 수 있음. 
		Page<Member> result = memberService.selectNameLike(name, pageable);
		//레코드를 List로 인출 
		List<Member> content = result.getContent();
		//레코드 갯수
		long totalElements = result.getTotalElements();
		//전체 페이지수
		int totalPages = result.getTotalPages();
		//페이지 당 레코드 수
		int size = result.getSize();
		//현재 페이지 번호. 0부터 시작이므로 1을 더해줌 
		int pageNumber = result.getNumber() + 1; 
		//컨텐츠의 갯수
		int numberOfElements = result.getNumberOfElements();
		
		//위 모든 정보를 Model에 저장 
		model.addAttribute("members", content);
		model.addAttribute("totalElements", totalElements);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("size", size);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("numberOfElements", numberOfElements);
		
		return "member_list";
	}
    		
    		
    		
    		/*@RequestParam("name") String search, 
    							@RequestParam("page") String page, 
    							Model model) {
    	
    	System.out.println("***"+ search +"***");
    	System.out.println("***"+ page +"***");
    	
    	//검색어로 시작하는 문자열 select
    	String name = search +"%";
    	//이름을 기준으로 내림차순 정렬
    	Sort sort = Sort.by(Sort.Order.desc("name"));
    	int nPage = Integer.parseInt(page) - 1;
    	
    	//페이지는 0부터 시작한다. 한페이지에 10개씩 출력한다. 
    	Pageable pageable = PageRequest.ofSize(10).withPage(nPage).withSort(sort);
    	
    	//서비스의 메서드를 호출하면 필요한 모든 정보를 반환받을 수 있다. 
    	Page<Member> result = memberService.selectNameLike(name, pageable);
    	List<Member> content = result.getContent();    	
    	long totalElements = result.getTotalElements();
    	int totalPages = result.getTotalPages();
    	int size = result.getSize();
    	int pageNumber = result.getNumber() + 1; //0부터 시작하므로 1을 더한다.
    	int numberOfElements = result.getNumberOfElements();//content의 갯수

    	model.addAttribute("members", content);
    	model.addAttribute("totalElements", totalElements);
    	model.addAttribute("totalPages", totalPages);
    	model.addAttribute("size", size);
    	model.addAttribute("pageNumber", pageNumber);
    	model.addAttribute("numberOfElements", numberOfElements);

   		return "select_name_list";
    }*/
	
}
