package com.edu.springboot.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
//  public List<Member> selectNameLike(String search, Pageable pageable) 
//  {
//  	List<Member> member = memberRepository.findByNameLike(search, pageable);
//      return member;
//  }
	
	public Page<Member> selectNameLike(String search, Pageable pageable){
		Page<Member> member = memberRepository.findByNameLike(search, pageable);
		return member;
	}	 
}
