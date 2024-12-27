package com.edu.springboot.firebase;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
	private String token;
	private String title;
	private String body;
	private String image;
	private String add_data;
//	private Map<String, String> data;
}


