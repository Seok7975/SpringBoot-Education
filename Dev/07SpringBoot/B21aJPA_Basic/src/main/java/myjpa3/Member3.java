package myjpa3;

import java.time.LocalDate;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

//JpaMember3 엔티티 생성
@Entity
@Table(name="JpaMember3")
public class Member3 {
	//PK 컬럼 지정
	@Id
	private String email;
	
	//문자형 컬럼 
	private String name;
	
	//create_date로 컬럼 생성
	@Column(name = "create_date")
	private LocalDate createDate;
	
	//생성자와 게터
	public Member3() {}
	public Member3(String email, String name, LocalDate createDate) {
		super();
		this.email = email;
		this.name = name;
		this.createDate = createDate;
	}
	public String getEmail() {
		return email;
	}
	public String getName() {
		return name;
	}
	public LocalDate getCreateDate() {
		return createDate;
	}
	
	//이름 변경을 위한 메서드 
	public void changeName(String newName) {
		this.name = newName;
	}
	
}
