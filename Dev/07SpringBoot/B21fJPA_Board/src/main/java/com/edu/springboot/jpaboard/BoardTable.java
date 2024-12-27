package com.edu.springboot.jpaboard;

import java.time.LocalDate;

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
@Entity(name="springboard")
public class BoardTable {
	@Id
	@SequenceGenerator (
		name = "mySequence",
		sequenceName = "springboard_seq",
		initialValue = 1,
		allocationSize = 1
	)
	@GeneratedValue (generator = "mySequence")
	private Long idx;
	private String name;
	private String title;
	private String contents;
	private LocalDate postdate;
	private Long hits;
	private Long bgroup;
	private Long bstep;
	private Long bindent;
	private String pass;
}
