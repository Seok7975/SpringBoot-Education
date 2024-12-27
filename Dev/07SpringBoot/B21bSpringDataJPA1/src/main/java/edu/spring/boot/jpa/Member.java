package edu.spring.boot.jpa;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//게터, 세터, toString등 추가
@Data
//인자생성자
@AllArgsConstructor
//디폴트생성자를 protected로 선언
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//빌더패턴을 적용하여 메서드 체이닝을 통해 초기화할 수 있다. 
//@Builder : 인스턴스 생성시 빌더 패턴을 통해 초기화할수 있게 코드를 추가해준다. 
@Builder
@Entity(name = "JPAMEMBER01")
public class Member {
	//PK, 시퀀스 생성
	@Id
	@GeneratedValue
	private Long id;
	
	private String username;
	
	//name속성에 지정한 이름으로 컬럼생성 
	@Column(name = "create_date")
	private LocalDate createDate;
}
