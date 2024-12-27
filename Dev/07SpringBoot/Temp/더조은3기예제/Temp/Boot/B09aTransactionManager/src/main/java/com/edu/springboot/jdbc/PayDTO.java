package com.edu.springboot.jdbc;

import lombok.Data;

/* transaction_pay 테이블과 매칭되는 DTO객체
구매한 티켓의 판매금액을 입력한다. */
@Data
public class PayDTO {
	private String userid;
    private int amount;
}
