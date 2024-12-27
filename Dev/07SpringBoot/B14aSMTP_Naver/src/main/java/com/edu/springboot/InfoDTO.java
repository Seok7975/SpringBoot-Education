package com.edu.springboot;

import lombok.Data;

//메일작성폼과 동일하게 DTO구성 
@Data
public class InfoDTO {
	private String mailServer;
	private String from;
	private String to;
	private String subject;
	private String format;
	private String content;
}