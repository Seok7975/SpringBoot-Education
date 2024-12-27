package com.edu.springboot.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity(name="JPAMEMBER03")
public class Member {
	//id컬럼을 PK로 지정
    @Id
    /* JPA에서 사용할 시퀀스 정의. 시퀀스 생성기의 이름은 mySequence03
    오라클에 생성될 시퀀스명은 JPAMEMBER03_SEQ
    초기값과 증가치는 1로 설정 */
	@SequenceGenerator (
		name = "mySequence03",
		sequenceName = "JPAMEMBER03_SEQ",
		initialValue = 1,
		allocationSize = 1
	)
    //지정된 이름을 통해 id값을 자동으로 생성 
	@GeneratedValue (generator = "mySequence03")
    private Long id;
    private String name;
	private String email;
}
