package com.edu.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.edu.springboot.jdbc.AddMember;
import com.edu.springboot.jdbc.ITicketService;
import com.edu.springboot.jdbc.PayDTO;
import com.edu.springboot.jdbc.TicketDTO;

import jakarta.servlet.http.HttpServletRequest;

/*
required(0)** : default값. 전체적으로 트랜잭션 처리한다. 
supports(1) : 기존 트랜잭션에 의존
mandatory(2) : 트랜잭션에 꼭 포함되어야 함. 트랜잭션이 있는 곳에서
	호출해야됨.
requires_new(3)** : 클래스 별로 정의된 트랜잭션을 각각 처리함. 
	즉 전파되지 않음
not_supported(4) :  트랜잭션에 포함하지않음. 기존의 트랜잭션이 존재하면
	일시중지하고 메서드 실행이 끝난후에 트랜잭션을 계속 진행한다. 
never(5) : 트랜잭션에 절대 포함하지않음. 트랜잭션이 있는 곳에서 호출하면
	에러발생. 
 */
@Controller
public class MainController {
	
	@Autowired
	ITicketService dao;
 
	//트랜잭션 템플릿 사용을 위한 자동주입
	@Autowired
	TransactionTemplate transactionTemplate;
	
	/* 추가작업 클래스 : 회원테이블에 구매한 사람의 이력을 기록한다. */
	@Autowired
	AddMember addMember;
		
	@RequestMapping("/")
	public String home() {
		return "home";
	}	
	
	//티켓구매
	@RequestMapping(value="/buyTicket.do", method=RequestMethod.GET)
	public String buy1() {				 
		return "buy";       
	}
	@RequestMapping(value="/buyTicket.do", method=RequestMethod.POST)
	public String buy2(TicketDTO ticketDTO, PayDTO payDTO, 
			HttpServletRequest req, Model model) {		
		String viewPath = "success";		
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(
						TransactionStatus status) {
					
					String errFlag = req.getParameter("err_flag");

					//1.회원 정보 입력(추가작업, 외부클래스에서 처리)
					addMember.memberInsert(ticketDTO, errFlag);

					//2.DB처리1
					payDTO.setAmount(ticketDTO.getT_count() * 10000);
					int result1 = dao.payInsert(payDTO);
					if(result1==1) 
						System.out.println("transaction_pay 입력성공");
					
					//3.비즈니스로직 처리(의도적인 에러발생)
					if(errFlag!=null && errFlag.equals("1")) {
						int money = Integer.parseInt("100원"); 
					}
					
					//4.DB처리2
					int result2 = dao.ticketInsert(ticketDTO);
					if(result2==1) 
						System.out.println("transaction_ticket 입력성공");
					
					model.addAttribute("ticketDTO", ticketDTO);
					model.addAttribute("payDTO", payDTO);
				}
			});
		}
		catch (Exception e) {
			//e.printStackTrace();
			System.out.println("transaction 테이블 Rollback됨");
			viewPath = "error";			 
		}		
		return viewPath;       
	}
}




