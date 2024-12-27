package com.edu.springboot.jdbc;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IMemberService {
	
	//회원목록(검색X)
	//public List<MemberDTO> select();
	//회원목록(검색O)
	public List<MemberDTO> select(MemberDTO memberDTO);
	
	//회원등록 : request 내장객체를 통해 받은 파라미터를 전달한다. 
	public int insert(String id, String pass, String name);
	
	/*
	회원정보가져오기 : 파라미터를 받은 후 @Param 어노테이션으로 Mapper에서 사용할
		이름으로 변경한다. 즉 id로 받은 후 _id로 변경한다. 
	 */
	public MemberDTO selectOne(@Param("_id") String id);
	
	//수정처리 : 파라미터가 저장된 Map을 매개변수로 사용한다. 
	public int update(Map<String, String> paramMap);
	
	//삭제처리 
	public int delete(String id);
}



