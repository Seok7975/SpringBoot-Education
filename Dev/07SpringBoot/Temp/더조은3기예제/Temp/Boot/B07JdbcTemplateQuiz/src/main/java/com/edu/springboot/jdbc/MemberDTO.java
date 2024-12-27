package com.edu.springboot.jdbc;

import lombok.Data;

//musthave 계정의 member 테이블 사용을 위한 DTO클래스
@Data
public class MemberDTO {
	private String id;
    private String pass;
    private String name;
    private String regidate;
        
    //검색 기능 추가를 위해 테이블과 상관없이 멤버변수 추가
    private String searchField;//member 테이블의 컬럼명
    private String searchKeyword;//검색어 
}
