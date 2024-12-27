package com.edu.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.edu.springboot.jdbc.ITicketService;
import com.edu.springboot.jdbc.PayDTO;
import com.edu.springboot.jdbc.TicketDTO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController {
	
	//Mybatis를 사용하기 위한 자동주입 
	@Autowired
	ITicketService dao;
	
	/*
	트랜잭션 처리를 위한 빈 자동주입. 해당 빈은 별도의 설정없이 스트링 컨테이너가
	준비해준것을 즉시 주입받을 수 있다. 즉 Spring boot에서 기본으로 제공된다. 
	 */
	@Autowired
	PlatformTransactionManager transactionManager;
	@Autowired
	TransactionDefinition definition;
		
	@RequestMapping("/")
	public String home() {
		return "home";
	}	
	
	//구매페이지로 진입 
	@RequestMapping(value="/buyTicket.do", method=RequestMethod.GET)
	public String buy1() {				 
		return "buy";       
	}
	//구매처리 
	@RequestMapping(value="/buyTicket.do", method=RequestMethod.POST)
	public String buy2(TicketDTO ticketDTO, PayDTO payDTO, 
			HttpServletRequest req, Model model) {
		
		//구매에 성공한 경우 포워드 할 View의 경로 
		String viewPath = "success";
		/*
		자동주입된 빈을 통해 Status 인스턴스를 생성한다. 이를 통해 작업에 대한 
		트랜잭션 처리를 할 수 있다. 전체작업에 대해 성공이면 commit, 실패라면
		rollback처리를 하게된다. 
		 */
		TransactionStatus status = 
				transactionManager.getTransaction(definition);
		try {
			//1.DB처리1 : 구매금액에 관련된 입력처리. 구매장수 * 10000원 
			payDTO.setAmount(ticketDTO.getT_count() * 10000);
			int result1 = dao.payInsert(payDTO);
			if(result1==1) 
				System.out.println("transaction_pay 입력성공");
			
			//2.비즈니스 로직 처리(의도적인 에러발생 부분)
			String errFlag = req.getParameter("err_flag");
			if(errFlag!=null) {
				/*
				구매페이지에서 체크박스에 체크한 경우 이 부분이 실행되어
				NumberFormatException이 발생된다. 
				 */
				int money = Integer.parseInt("100원"); 
			}
			
			/*
			3.DB처리2 : 구매한 티켓 매수에 대한 처리로 6장 이상이면 check 제약조건
			위배로 에러가 발생한다. 
			*/
			int result2 = dao.ticketInsert(ticketDTO);
			if(result2==1) 
				System.out.println("transaction_ticket 입력성공");
			
			model.addAttribute("ticketDTO", ticketDTO);
			model.addAttribute("payDTO", payDTO);
			
			//모든 작업에 대해 문제가 없다면 커밋한다. 
			transactionManager.commit(status);	
		}
		catch (Exception e) {
			e.printStackTrace();
			/* 
			위 3개의 단위작업중 하나라도 오류가 발생되면 전체 작업을 롤백한 후
			에러페이지로 포워드한다. 
			*/
			viewPath = "error";
			transactionManager.rollback(status);
		}
		return viewPath;       
	}
}







