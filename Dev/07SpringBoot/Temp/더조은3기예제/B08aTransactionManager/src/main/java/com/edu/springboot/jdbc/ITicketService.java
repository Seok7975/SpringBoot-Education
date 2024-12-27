package com.edu.springboot.jdbc;

import org.apache.ibatis.annotations.Mapper;

/*
구매한 티켓과 금액에 대한 insert 처리를 위한 추상 메서드 정의 
 */
@Mapper
public interface ITicketService {
	public int ticketInsert(TicketDTO ticketDTO);
	public int payInsert(PayDTO payDTO);
}
