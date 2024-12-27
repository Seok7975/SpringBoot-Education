package com.edu.springboot.jdbc;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
 
@Mapper
public interface IBoardService {
	public int getTotalCount(ParameterDTO parameterDTO);
	public ArrayList<BoardDTO> listPage(ParameterDTO parameterDTO);
	public int write(@Param("_name") String name, 
			@Param("_title") String title,
			@Param("_content") String content);
	public int plusVisitCount(BoardDTO boardDTO);
	public BoardDTO view(BoardDTO boardDTO);
	public int edit(BoardDTO boardDTO);
	public int delete(String idx);
}

