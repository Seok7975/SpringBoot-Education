package com.edu.springboot.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
DAO 역할의 인터페이스. 
타입매개변수는 테이블로 사용할 Member와 기본키로 설정할 필드의 타입을 명시한다. 
long은 기본자료형이므로 랩퍼클래스인 Long으로 기술해야한다. 
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> 
{
    // 기본적인 Create, Read, Update, Delete 기능이 자동으로 생성된다.
	
	// findBy 뒤에 컬럼명을 붙여주면 이를 이용한 검색이 가능하다. 
	Optional<Member> findByName(String keyword);
	Optional<Member> findByEmail(String keyword);
	
	// 다음과 같이 다양한 확장이 가능하다. 
	// like절 추가
	List<Member> findByNameLike(String keyword);
	// 정렬 기능 추가 
	List<Member> findByNameLikeOrderByNameDesc(String keyword);
	List<Member> findByNameLikeOrderByNameAscEmailDesc(String keyword);	
	
	//정렬의 조건때문에 메서드명이 길어지게 되므로 Sort객체로 검색의 조건을 추가할 수 있다. 
	List<Member> findByNameLike(String keyword, Sort sort);
}
