package com.edu.springboot.jdbc;

import java.util.List;

import org.springframework.stereotype.Service;

/*
컨트롤러와 DAO사이에서 매개 역할을 하는 인터페이스로 DAO 클래스의 부모
역할을 한다. 
@Service 어노테이션은 @Controller 와 동일하게 스프링 컨테이너가 시작될때
자동으로 Scan되어 빈이 생성된다. 따라서 이 클래스도 기본패키지 하위에 있어야
한다. 
 */
@Service
public interface IMemberService {
	/*
	추상메서드 정의시 매개변수는 일괄적으로 DTO클래스를 사용하고 있다. 
	커맨드객체를 사용하면 파라미터를 한꺼번에 받아 전달할 수 있고, 
	파라미터 갯수에 변화가 생기더라도 DTO만 수정하면 되므로 프로그램 
	작성이 훨씬 쉬워진다. 
	 */
	//회원정보추가
	public int insert(MemberDTO memberDTO);
	//회원목록(리스트)
	public List<MemberDTO> select(MemberDTO memberDTO);
	//회원정보조회
	public MemberDTO selectOne(MemberDTO memberDTO);
	//회원정보수정
	public int update(MemberDTO memberDTO);
	//회원정보삭제(탈퇴)
	public int delete(MemberDTO memberDTO);
}

