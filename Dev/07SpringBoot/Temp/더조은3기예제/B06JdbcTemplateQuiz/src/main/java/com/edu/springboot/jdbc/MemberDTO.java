package com.edu.springboot.jdbc;

import lombok.Data;

//musthave 계정의 member 테이블의 컬럼과 동일하게 작성 
@Data
public class MemberDTO {
	private String id;
    private String pass;
    private String name;
    private String regidate;
    
    //검색을 위한 멤버변수 추가 
    private String searchField;
    private String searchKeyword;
}
