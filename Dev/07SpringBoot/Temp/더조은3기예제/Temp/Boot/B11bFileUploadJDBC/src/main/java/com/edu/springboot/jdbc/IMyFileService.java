package com.edu.springboot.jdbc;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IMyFileService {
	public int insertFile(MyFileDTO myFileDTO);
}

