package com.nyj.exam.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyj.exam.demo.repository.ArticleRepository;
import com.nyj.exam.demo.repository.MemberRepository;
import com.nyj.exam.demo.vo.Article;
import com.nyj.exam.demo.vo.Member;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	 
	public int doJoin(String loginId, String loginPw, String email, String name, String nickname, String phoneNumber) {
		
		Member findMember = getMemberByLoginId(loginId);
		
		if(findMember != null) {
			return -1;
		} 		
		
		findMember = getMemberbyEmail(email);
		
		if(findMember != null) {
			return -2;
		} 		
			
		memberRepository.doJoin(loginId, loginPw, email, name, nickname, phoneNumber);
		return memberRepository.last_insert_id();
	}
	
	public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}
	
	public Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}
	
	private Member getMemberbyEmail(String email) {
		return memberRepository.getMemberbyEmail(email);
		
	}
	
}
