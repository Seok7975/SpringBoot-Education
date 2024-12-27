package com.edu.springboot.jdbc;

import lombok.Data;

//musthave 계정의 member 테이블 사용을 위한 DTO클래스
@Data
public class MemberDTO {
	private String id;
    private String pass;
    private String name;
    private String regidate;
}
