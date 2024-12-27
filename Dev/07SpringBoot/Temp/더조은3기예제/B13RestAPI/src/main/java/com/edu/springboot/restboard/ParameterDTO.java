package com.edu.springboot.restboard;

import java.util.ArrayList;

import lombok.Data;

@Data
public class ParameterDTO {		
	private String num;
	private String pageNum;
	private String searchField;
	private ArrayList<String> searchWord;
	private int start; 	
	private int end;
}
