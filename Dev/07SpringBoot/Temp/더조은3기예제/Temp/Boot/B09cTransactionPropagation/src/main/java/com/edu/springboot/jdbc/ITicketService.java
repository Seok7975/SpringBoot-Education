package com.edu.springboot.jdbc;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ITicketService {
	
	//티켓구매 처리를 위한 추상메서드
	public int ticketInsert(TicketDTO ticketDTO);
	public int payInsert(PayDTO payDTO);
	
	//회원이력을 남기기 위한 추상메서드 추가
	public int memberRegist(TicketDTO ticketDTO);	
}
