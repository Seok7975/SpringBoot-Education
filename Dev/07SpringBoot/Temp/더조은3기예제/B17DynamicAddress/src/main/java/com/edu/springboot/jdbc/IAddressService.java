package com.edu.springboot.jdbc;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IAddressService {
	//조건없이 전체 시도를 인출하는 쿼리문 실행 
	public List<AddressDTO> selectSido();
	//시/도를 선택하면 이에 해당하는 구/군을 인출하는 쿼리문 실행 
	public List<AddressDTO> selectGugun(AddressDTO addressDTO);	
}