package com.edu.springboot.jdbc;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
 
@Mapper
public interface IBoardService {
	//Paging처리를 위해 게시물의 갯수를 카운트 
	public int getTotalCount(ParameterDTO parameterDTO);
	
	//게시판 목록(페이징 기능 추가)
	public ArrayList<BoardDTO> listPage(ParameterDTO parameterDTO);
	
	//작성하기(받은 폼값은 이름을 변경한 후 매퍼로 전달한다)
	public int write(@Param("_name") String name, 
			@Param("_title") String title,
			@Param("_content") String content);
	
	//내용보기
	public BoardDTO view(BoardDTO boardDTO);	
	//수정하기
	public int edit(BoardDTO boardDTO);
	
	//삭제하기
	public int delete(String idx);
}

