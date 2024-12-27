package com.edu.springboot.jdbc;

import lombok.Data;

/* transaction_ticket 테이블과 매칭
티켓의 매수를 입력한다. 테이블에는 check 제약조건으로 5장 이하로만
구매할수 있게 되어있다. */
@Data
public class TicketDTO {
	private String userid;
    private int t_count;
}
