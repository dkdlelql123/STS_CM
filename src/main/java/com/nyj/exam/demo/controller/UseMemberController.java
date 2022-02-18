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
import com.nyj.exam.demo.util.Util;
import com.nyj.exam.demo.vo.Article;
import com.nyj.exam.demo.vo.Member;
import com.nyj.exam.demo.vo.ResultData; 

@Controller
public class UseMemberController { 
	
	@Autowired
	private MemberService memberService;

	//
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public Object doJoin(String loginId, String loginPw, String email, String name, String nickname, String phoneNumber) {

		if(Util.empty(loginId)) return "아이디를 입력해주세요.";
		if(Util.empty(loginPw)) return "비밀번호를 입력해주세요.";
		if(Util.empty(email)) return "이메일을 입력해주세요.";
		if(Util.empty(name)) return "이름을 입력해주세요.";
		if(Util.empty(nickname)) return "닉네임을 입력해주세요.";
		if(Util.empty(phoneNumber)) return "전화번호를 입력해주세요.";
		
		int memberId = memberService.doJoin(loginId, loginPw, email, name, nickname, phoneNumber);
		
		if(memberId == -1) {
			return ResultData.form("f-1", Util.f("중복되는 아이디(%s)가 있습니다.", loginId)); 
		}
		
		if(memberId == -2) {
			return ResultData.form("f-2",  Util.f("중복되는 이메일(%s)[%s]가 있습니다.", email, name));
		}
		
		Member member = memberService.getMemberById(memberId);
		
		return ResultData.form("s-1",  Util.f("%s님 회원가입이 완료되었습니다", loginId), member);
	}	
}
