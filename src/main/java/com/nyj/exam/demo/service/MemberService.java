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
		memberRepository.doJoin(loginId, loginPw, email, name, nickname, phoneNumber);
		return memberRepository.last_insert_id();
	}
	
	public Member getMember(int id) {
		return memberRepository.getMember(id);
	}
}
