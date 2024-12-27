package edu.spring.boot.jpa;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JpaController {

	//서비스 빈 자동주입
	@Autowired
	MemberService memberService;
	
	//입력 
	@GetMapping("/insert")
	public String insert(@RequestParam("username") String name, Model model) {
		//엔티티의 빌더 패턴을 통해 인스턴스 초기화 
		Member member = Member.builder()
				.username(name)
				.createDate(LocalDate.now())
				.build();
		//영속성에 입력 처리 
		Member result = memberService.insert(member);
		//모델에 저장 및 뷰 포워드 
		model.addAttribute("member", result);		
		return "insert";
	}
	//조회
	@GetMapping("/select")
    public String select(@RequestParam("id") Long p_id, Model model) {
		//파라미터로 전달된 id로 조회
        Optional<Member> result = memberService.select(p_id);        
        if (result.isPresent()) {
        	//존재하면 레코드 저장
       		model.addAttribute("member", result.get());
        }
        else {
        	//없으면 null 저장
       		model.addAttribute("member", null);
        }
   		return "select";
    }
	//전체조회
	@GetMapping("/selectAll")
    public String selectAll(Model model) {
		//2개 이상의 레코드일수 있으므로 List로 반환 
        List<Member> result = memberService.selectAll();
   		model.addAttribute("members", result);
   		return "selectAll";
    }
	//삭제
	@GetMapping("/delete")
    public String delete(@RequestParam("id") Long pid) {
		memberService.delete(pid);        
        return "delete";
    }
	//수정 
	@GetMapping("/update")
    public String update(Member member, Model model) {
    	member.setCreateDate(LocalDate.now());
        Member result = memberService.update(member);        
   		model.addAttribute("member", result);
   		return "update";
    }
}
