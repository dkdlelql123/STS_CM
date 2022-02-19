package com.nyj.exam.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyj.exam.demo.repository.ArticleRepository;
import com.nyj.exam.demo.repository.MemberRepository;
import com.nyj.exam.demo.util.Util;
import com.nyj.exam.demo.vo.Article;
import com.nyj.exam.demo.vo.Member;
import com.nyj.exam.demo.vo.ResultData;

@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	 
	public ResultData doJoin(String loginId, String loginPw, String email, String name, String nickname, String phoneNumber) {
		
		Member findMember = getMemberByLoginId(loginId);
		
		if(findMember != null) {
			return ResultData.form("f-1", Util.f("중복되는 아이디(%s)가 있습니다.", loginId)); 
		}
		
		findMember = getMemberbyEmail(email);
		
		if(findMember != null) {
			return ResultData.form("f-2",  Util.f("중복되는 이메일(%s)[%s]가 있습니다.", email, name));
		} 		
			
		memberRepository.doJoin(loginId, loginPw, email, name, nickname, phoneNumber);
		int lastInsertId = memberRepository.last_insert_id();
		
		return ResultData.form("s-1",  "회원가입에 성공하셨습니다.", lastInsertId);
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
