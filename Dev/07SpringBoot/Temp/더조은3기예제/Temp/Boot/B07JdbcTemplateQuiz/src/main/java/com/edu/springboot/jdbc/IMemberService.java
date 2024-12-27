package com.edu.springboot.jdbc;

import java.util.List;

import org.springframework.stereotype.Service;

/*
컨트롤러와 DAO사이에서 매개 역할을 하는 인터페이스로 DAO클래스의 부모 역할을
한다. 
@Service 어노테이션은 @Controller와 동일하게 컨테이너가 시작될때 자동으로 Scan
되는 기능을 가지고 있다. 이를 통해 자동으로 빈이 생성된다. 
*/
@Service
public interface IMemberService {
	/* 추상메서드 정의시 매개변수는 일괄적으로 DTO객체를 사용하고 있다. 
	커맨드 객체를 사용하면 파라미터를 한꺼번에 받아 전달할 수 있고, 갯수에
	변화가 생기더라도 DTO객체만 수정하면 되므로 프로그램 작성이 쉬워진다. */
	//회원 정보 추가
	public int insert(MemberDTO memberDTO);
	//회원 목록(리스트)
	public List<MemberDTO> select();
	//회원 정보 조회
	public MemberDTO selectOne(MemberDTO memberDTO);
	//회원 정보 수정
	public int update(MemberDTO memberDTO);
	//회원 정보 삭제(탈퇴)
	public int delete(MemberDTO memberDTO);
	
	//회원 목록-검색기능추가
	public List<MemberDTO> selectSearch(MemberDTO memberDTO);
}




