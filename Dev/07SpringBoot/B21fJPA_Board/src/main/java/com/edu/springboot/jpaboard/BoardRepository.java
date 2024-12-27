package com.edu.springboot.jpaboard;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
	//기본적인 CRUD 기능 지원
	
	//전체 레코드 인출 및 정렬
	List<BoardEntity> findAll(Sort sort);
}
