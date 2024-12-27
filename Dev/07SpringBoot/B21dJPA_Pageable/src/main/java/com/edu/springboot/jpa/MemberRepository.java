package com.edu.springboot.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> 
{
    // 기본적인 Create, Read, Update, Delete 기능이 자동으로 생성된다.
	/*
	반환타입을 List로 하면 엔티티에서 인출한 목록만 반환한다. 즉 Pageable 파라미터에 의해
	페이징된 ResultSet만 반환하게된다.	
	 */
	//List<Member> findByNameLike(String keyword, Pageable pageable);
	
	/*
	하지만 반환타입을 Page로 설정하면 인출된 ResultSet을 포함하여, 페이징에 관련된 다양한
	정보를 반환하게된다. 총 페이지수, 레코드갯수 등이 포함된다. 
	 */
	Page<Member> findByNameLike(String keyword, Pageable pageable);
}
