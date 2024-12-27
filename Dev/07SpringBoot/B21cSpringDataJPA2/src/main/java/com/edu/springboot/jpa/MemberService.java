package com.edu.springboot.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	//DAO 자동주입
	@Autowired
	private MemberRepository memberRepository;
	//레코드 입력
	public void insert() {
    	Member member;
    	
    	member = Member.builder().name("이순신").email("test1@test.com").build();
        memberRepository.save(member);

        member = Member.builder().name("강감찬").email("test2@test.com").build();
        memberRepository.save(member);

        member = Member.builder().name("을지문덕").email("test3@test.com").build();
        memberRepository.save(member);

        member = Member.builder().name("계백").email("test4@test.com").build();
        memberRepository.save(member);

        member = Member.builder().name("김유신").email("test5@test.com").build();
        memberRepository.save(member);

        member = Member.builder().name("연개소문").email("test6@test.com").build();
        memberRepository.save(member);

        member = Member.builder().name("양만춘").email("test7@test.com").build();
        memberRepository.save(member);

        member = Member.builder().name("김종서").email("test8@test.com").build();
        memberRepository.save(member);

        member = Member.builder().name("최영").email("test9@test.com").build();
        memberRepository.save(member);
    }
	//전체조회
    public List<Member> selectAll() {
        return memberRepository.findAll();
    }
    //아이디로 검색
    public Optional<Member> selectId(Long search) {
        Optional<Member> member = memberRepository.findById(search);
        return member;
    }
    //이름으로 검색
    public Optional<Member> selectName(String search) {
    	Optional<Member> member = memberRepository.findByName(search);
        return member;
    }
    //이메일로 검색
    public Optional<Member> selectEmail(String search) {
    	Optional<Member> member = memberRepository.findByEmail(search);
        return member;
    }
    //이름을 like로 검색
    public List<Member> selectNameLike(String search) {
    	List<Member> member = memberRepository.findByNameLike(search);
        return member;
    }
    //이름을 like로 검색 후 내림차순 정렬
    public List<Member> selectNameLikeNameDesc(String search) {
    	List<Member> member = memberRepository.findByNameLikeOrderByNameDesc(search);
        return member;
    }
    //Sort 빈을 통해 정렬 
    public List<Member> selectNameLike(String search, Sort sort) {
    	List<Member> member = memberRepository.findByNameLike(search, sort);
        return member;
    }
}
