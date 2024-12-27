package com.edu.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.edu.springboot.jdbc.ITicketService;
import com.edu.springboot.jdbc.PayDTO;
import com.edu.springboot.jdbc.TicketDTO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController {
	
	@Autowired
	ITicketService dao;
 
	/* 트랜잭션 메니져 관련 빈은 삭제한 후 트랜잭션 템플릿 사용을
	위한 빈을 자동주입 받는다. */
	@Autowired
	TransactionTemplate transactionTemplate;
		
	@RequestMapping("/")
	public String home() {
		return "home";
	}	
	
	//티켓구매
	@RequestMapping(value="/buyTicket.do", method=RequestMethod.GET)
	public String buy1() {				 
		return "buy";       
	}
	//티켓 구매 처리
	@RequestMapping(value="/buyTicket.do", method=RequestMethod.POST)
	public String buy2(TicketDTO ticketDTO, PayDTO payDTO, HttpServletRequest req, 
			Model model) {		
		
		//구매에 성공했을때 포워드할 View의 경로
		String viewPath = "success";
		
		/* 트랜잭션 메니져 사용을 위한 Status객체 생성 부분은 
		필요없으므로 삭제한다. */
		
		try {
			/* 템플릿 내에 익명클래스를 통해 오버라이딩된 된 메서드로 모든 
			비즈니스 로직을 옮겨주면 된다. 템플릿을 사용하면 commit, rollback을
			별도로 기술하지 않아도 알아서 처리된다. 
			 */
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(
						TransactionStatus status) {
					//DB처리1
					payDTO.setAmount(ticketDTO.getT_count() * 10000);
					int result1 = dao.payInsert(payDTO);
					if(result1==1) 
						System.out.println("transaction_pay 입력성공");
					
					//비즈니스로직 처리(의도적인 에러발생)
					String errFlag = req.getParameter("err_flag");
					if(errFlag!=null) {
						int money = Integer.parseInt("100원"); 
					}
					
					//DB처리2
					int result2 = dao.ticketInsert(ticketDTO);
					if(result2==1) 
						System.out.println("transaction_ticket 입력성공");
					
					model.addAttribute("ticketDTO", ticketDTO);
					model.addAttribute("payDTO", payDTO);
				}
			});
		}
		catch (Exception e) {
			e.printStackTrace();
			//예외가 발생하면 포워드 할 View의 경로 
			viewPath = "error";			 
		}
		
		return viewPath;       
	}
}
