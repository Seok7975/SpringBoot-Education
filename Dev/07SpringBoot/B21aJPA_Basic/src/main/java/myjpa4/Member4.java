package myjpa4;

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

//JpaMember3과 동일하게 엔티티 생성
@Entity
@Table(name="JpaMember4")
public class Member4 {
	
	@Id
	private String email;
	private String name;
	@Column(name = "create_date")
	private LocalDate createDate;
	
	public Member4() {}
	public Member4(String email, String name, LocalDate createDate) {
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
	
	public void changeName(String newName) {
		this.name = newName;
	}
	
}
