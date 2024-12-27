package com.edu.springboot.jdbc;

import lombok.Data;

//zipcode 테이블과 동일하게 멤버변수 선언 
@Data
public class AddressDTO {	
    private String zipcode;
    private String sido;
    private String gugun;
    private String dong;
    private String bunji;
    private String seq;
}