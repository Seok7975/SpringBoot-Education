package myjpa1c;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*
JPA에서는 클래스명를 통해 테이블을 생성한다. 
멤버변수는 컬럼을 생성하게 되는데, 여러가지 어노테이션을 통해
세부적인 설정이 가능하다.   
 
@Entity : 클래스명으로 테이블을 생성한다.
@Table : 테이블 생성시 이름을 지정할 수 있다.
	즉, 별도의 지정이 없으면 클래스명이 default 테이블명이 된다.
*/

/* JPA의 엔티티라는 의미. 테이블과 매핑되어있음 */
@Entity
/* 실제 테이블명 지정. 지정하지 않으면 member1로 테이블 생성됨. */
@Table(name="JpaMember1")
public class Member1 {
	
	/*
	@Id : 컬럼 생성시 primary key로 지정한다. 
	@GeneratedValue : 기본키에서 사용할 시퀀스를 생성한다. 
		별도의 설정이 없으면 증가치를 50으로 설정한다. 
		시퀀스명은 "테이블명_SEQ"로 지정된다. 
		MySQL이라면 auto_increment(자동증가) 컬럼으로 지정된다. 
	 */
	/* primary key로 지정*/
	@Id
	/* 시퀀스가 적용되는 컬럼. MySQL이라면 AUTO_INCREMENT로 지정된
	컬럼을 사용한다. 또한 jpamember1_seq 라는 이름으로 시퀀스가 생성된다.
	이때 증가치는 50으로 지정된다.  */
	@GeneratedValue 
	private Long id;
	/* 별다른 설정이 없으므로 변수명으로 컬럼 생성 */
	private String username;
	
	/* 변수명이 아닌 name에 지정된 이름으로 컬럼이 생성.  */
	@Column(name="create_data")
	//날짜 타입으로 설정 
	private LocalDate createDate;	
	
	/*	
	@Temporal(TemporalType.TIMESTAMP)
	pravate Date createDate;
	=> 타입을 Date로 지정하면 날짜타입으로 인식하지 못하므로 이와같이
 	선언해줘야한다. 
 	Java8에서 LocalDateTime이 추가된 후 거의 사용하지 않는다.  
	*/
	/*
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	Java에서 제공하는 Date클래스의 날짜형식과 오라클의 날짜형식이 서로 다르므로 어노테이션을
	통해 타입을 TIMESTAMP형식으로 맞춰준다. 
	- TemporalType.DATE : java.sql.Date에 매핑
	- TemporalType.TIME : java.sql.Time에 매핑
	- TemporalType.TIMESTAMP : java.sql.Timestamp에 매핑
		
	현재는 Date 대신 Java8에서 추가된 LocalDateTime을 더 많이 사용한다.
	 */

	//JPA가 디폴트생성자를 사용하므로 반드시 추가해야한다.  
	//기본생성자와 인수생성자
	/* 우리가 사용하기 위해 인수생성자를 정의하면 기본생성자를 반드시 추가해야한다. JPA가 
	사용하므로 없으면 에러가 발생한다. */
	public Member1() {}
	
	//인수생성자
	public Member1(String username, LocalDate createDate) {
		this.username = username;
		this.createDate = createDate;
	}
}
