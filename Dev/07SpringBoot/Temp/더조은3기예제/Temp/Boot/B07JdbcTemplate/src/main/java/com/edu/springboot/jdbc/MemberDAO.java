package com.edu.springboot.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

/* @Repository 어노테이션을 통해 컨테이너 시작시 자동으로 빈이 생성된다. 
@Controller, @Service와 동일한 역할을 한다. */
@Repository
public class MemberDAO implements IMemberService {
	
	/* IMemberService 인터페이스를 구현했으므로 정의된 추상메서드를 일괄적으로
	오버라이딩해야 한다. 컨트롤러에서는 서비스 인터페이스를 통해 DAO의 각 메서드를
	호출하게 된다.(상속에서는 부모의 추상메서드를 통해 오버라이딩된 자식의 
	메서드를 호출할 수 있다.) */
	
	/* JDBC작업을 위해 자동주입 받는다. JdbcTemplate 빈은 개발자가 직접 설정하지
	않고 build.gradle에 의존설정이 되어있으므로 컨테이너가 자동으로 만들어준다.*/
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//회원목록
	@Override
	public List<MemberDTO> select() {
		//회원 레코드를 가입일 기준으로 내림차순 정렬해서 인출한다. 
		String sql = "SELECT * FROM member "
				+ " ORDER BY regidate DESC";
		/*
		query() 메서드를 통해 select문을 실행한다. 쿼리문 실행후 반환되는 
		ResultSet은 RowMapper가 자동으로 반복하여 DTO에 저장한 후 List에 
		추가해서 반환해준다. 즉, 레코드를 컬렉션에 저장하기 위한 반복적인
		작업을 자동으로 수행해준다.  
		*/
		return jdbcTemplate.query(sql, 
			new BeanPropertyRowMapper<MemberDTO>(MemberDTO.class));
	}
	
	//회원등록 
	@Override
	public int insert(MemberDTO memberDTO) {
		/*
		insert, delete, update와 같이 행의 변화가 생기는 쿼리문은 update()
		메서드를 사용한다. 쿼리 실행후 적용된 행의 갯수를 int형으로 반환한다. 
		*/
		int result = jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) 
					throws SQLException {
				/* 인파라미터가 있는 insert 쿼리문의 실행을 위해 
				PreparedStatementCreator의 익명클래스를 사용한다. 
				내부에서 prepared 인스턴스를 생성한 후 인파라미터를 설정하고
				해당 객체를 반환한다. 이 객체를 update() 메서드가 실행하여
				결과를 반환하게된다. */
				String sql = "INSERT INTO member (id, pass, name) "
						+ " VALUES (?, ?, ?)";
				
				//prepared 객체 생성
				PreparedStatement psmt = con.prepareStatement(sql);
				//인파라미터 설정
				psmt.setString(1, memberDTO.getId());
				psmt.setString(2, memberDTO.getPass());
				psmt.setString(3, memberDTO.getName());
				//객체 반환 
				return psmt;
			}
		});		
		return result;       
	}
	
	//회원조회 
	@Override
	public MemberDTO selectOne(MemberDTO memberDTO) {	
		//인파라미터가 있는 select 쿼리문
		String sql = "select * from member where id=?";
		try {
			/* 하나의 레코드를 반환하는 select 쿼리문이므로 queryForObject
			메서드를 사용한다. 
			두번째 인수를 통해 인출한 레코드를 DTO에 자동으로 저장해준다.
			세번째 인수를 통해 쿼리문의 인파라미터를 설정한다. Object형 배열로
			순서대로 채워주게된다. 
			*/
			memberDTO = jdbcTemplate.queryForObject(sql, 
				new BeanPropertyRowMapper<MemberDTO>(MemberDTO.class), 
				new Object[] { memberDTO.getId() });
			/* queryForObject() 메서드는 무조건 하나의 결과를 반환하도록 설계
			되어있어, 반환값이 없는 경우 예외가 발생한다. 따라서 이 메서드를
			사용할때는 try~catch를 해주는것이 좋다. */
		}
		catch (Exception e) {
			System.out.println("예외가 발생하였습니다.");
			e.printStackTrace();
		}
		return memberDTO;       
	}
	//회원정보 수정 처리 
	@Override
	public int update(MemberDTO memberDTO) {		
		String sql = "UPDATE member SET pass=?, name=? "
				+ " WHERE id=?";
		/* 인파라미터가 있는 update 쿼리문을 실행하기 위해 
		PreparedStatementSetter의 익명클래스를 사용한다. update() 메서드의
		두번째 인수로 사용하고 오버라이딩된 메서드 내에서 인파라미터를
		설정한다. */
		int result = jdbcTemplate.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, memberDTO.getPass());
				ps.setString(2, memberDTO.getName());
				ps.setString(3, memberDTO.getId());
			}
		});		
		return result;  
	}
	
	//삭제 처리
	@Override
	public int delete(MemberDTO memberDTO) {
		String sql = "delete from member where id=?";
		int result = jdbcTemplate.update(sql, 
				new Object[] {memberDTO.getId()});
		return result;
	}	
}
