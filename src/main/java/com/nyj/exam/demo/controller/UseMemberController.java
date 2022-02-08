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

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public Member doJoin(String loginId, String loginPw, String email, String name, String nickname, String phoneNumber) {
		
		int memberId = memberService.doJoin(loginId, loginPw, email, name, nickname, phoneNumber);
		
		Member member = memberService.getMember(memberId);
		
		return member;
	}	
}
