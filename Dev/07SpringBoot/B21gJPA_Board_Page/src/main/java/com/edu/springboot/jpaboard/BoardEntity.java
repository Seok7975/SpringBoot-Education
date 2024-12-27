package com.edu.springboot.jpaboard;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity(name="jpaboard")
public class BoardEntity {
	@Id
	@SequenceGenerator (
		name = "mySequence",
		sequenceName = "jpaboard_seq",
		initialValue = 1,
		allocationSize = 1
	)
	@GeneratedValue (generator = "mySequence")
	private Long idx; //일련번호
	private String name; //이름
	private String title; //제목
	private String contents; //내용
	//private String pass; //비밀번호
	private Long hits; //조회수
	@Column(columnDefinition = "DATE DEFAULT SYSDATE")
	private LocalDateTime postdate; //작성일
	
	@PrePersist
	protected void onPrePersist() {
		//작성일 : 현재시각으로 설정
		this.postdate = LocalDateTime.now();
		//조회수 : 0으로 설정
		if (this.hits == null) {
            this.hits = 0L;
        }
	}
}










