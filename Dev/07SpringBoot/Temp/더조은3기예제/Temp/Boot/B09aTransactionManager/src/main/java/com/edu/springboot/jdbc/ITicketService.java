package com.edu.springboot.jdbc;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ITicketService {
	//구매한 티켓과 금액에 대한 insert처리를 위한 추상메서드 
	public int ticketInsert(TicketDTO ticketDTO);
	public int payInsert(PayDTO payDTO);
}
