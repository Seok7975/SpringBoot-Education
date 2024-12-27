package com.edu.springboot.jdbc;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ITbService {
	public List<TbDTO> tbList();
}
