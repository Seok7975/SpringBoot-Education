package myjpa2;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

//JPAMEMBER2로 엔티티 생성 
@Entity
@Table(name="JpaMember2")
public class Member2 {
	
	@Id
	/* 시퀀스 생성시의 설정. 시퀀스의 별칭을 mySequence01로 지정. 
	실제 생성되는 시퀀스명은 JpaMember2_SEQ. 초기값 1, 증가치 1로 설정. */
	@SequenceGenerator(
		name = "mySequence01",
		sequenceName = "JpaMember2_SEQ",
		initialValue = 1,
		allocationSize = 1
	) 
	/* 앞에서 생성된 시퀀스의 별칭을 통해 생성된 값을 사용 */
	@GeneratedValue(generator = "mySequence01")
	private Long id;
	
	//필드를 통해 접근해서 사용. @Access 어노테이션이 없으면 필드가 디폴트값. 
	@Access(AccessType.FIELD)
	private String username;
	
	//메서드(getter/setter)를 통해 접근해서 사용. 변수명으로는 접근할 수 없음. 
	@Access(AccessType.PROPERTY)
	private String password;

	//영속 대상에서 제외되는 항목. 어노테이션 혹은 키워드로 설정 가능. 
	@Transient
	private long timestamp1;
	transient private long timestamp2;
	
	//생성자 
	public Member2() {}
	public Member2(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	//패스워드에 대한 게터/세터
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
