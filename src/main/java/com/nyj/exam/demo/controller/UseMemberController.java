package com.nyj.exam.demo.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nyj.exam.demo.service.ArticleService;
import com.nyj.exam.demo.service.MemberService;
import com.nyj.exam.demo.vo.Article;
import com.nyj.exam.demo.vo.Member; 

@Controller
public class UseMemberController { 
	
	@Autowired
	private MemberService memberService;

	//회원가입 시, 입력데이터 유효성 체크, 공백도 체크
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public Object doJoin(String loginId, String loginPw, String email, String name, String nickname, String phoneNumber) {

		if(loginId == null || loginId.trim().length() == 0) return "아이디를 입력해주세요.";
		if(loginPw == null || loginPw.trim().length() == 0) return "비밀번호를 입력해주세요.";
		if(email == null || email.trim().length() == 0) return "이메일을 입력해주세요.";
		if(name == null || name.trim().length() == 0) return "이름을 입력해주세요.";
		if(nickname == null || nickname.trim().length() == 0) return "닉네임을 입력해주세요.";
		if(phoneNumber == null || phoneNumber.trim().length() == 0) return "전화번호를 입력해주세요.";
		
		int memberId = memberService.doJoin(loginId, loginPw, email, name, nickname, phoneNumber);
		
		if(memberId == -1) {
			return "중복되는 아이디가 있습니다.";
		}
		
		Member member = memberService.getMember(memberId);
		
		return member;
	}	
}
