package com.edu.springboot.jpaboard;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardDAO extends JpaRepository<BoardTable, Long>{

	@Query("select sb from SPRINGBOARD sb order sb.idx desc")
	List<BoardTable> selectList();
}

/**
JPQL(Java Persistence Query Language)

JPQL은 SQL을 추상화하여 특정 데이터베이스 SQL에 의존적이지 않은 객체지향 쿼리언어이다. 
테이블이 대상이 아니라, 엔티티(Entity)를 대상으로 쿼리를 실행하므로 객체지향 쿼리언어라고 불린다.

Ex) select m from Member as m where m.age>18
-엔티티와 속성은 대소문자를 구분한다. 정의한 그대로를 사용해야한다. 
-JPQL 키워드는 대소문자를 구분하지 않는다.  
-테이블의 아닌 엔티티의 이름을 사용한다. 
-별칭이 필수이다. 단 as는 생략할 수 있다. 




출처: https://ttl-blog.tistory.com/151 [Shin._.Mallang:티스토리]

*/