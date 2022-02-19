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
	public ResultData doJoin(String loginId, String loginPw, String email, String name, String nickname, String phoneNumber) {

		if(Util.empty(loginId)) return ResultData.form("f-1", Util.f("아이디를 입력해주세요."));
		if(Util.empty(loginPw)) return ResultData.form("f-1", Util.f("비밀번호를 입력해주세요."));
		if(Util.empty(email)) return ResultData.form("f-1", Util.f("이메일을 입력해주세요."));
		if(Util.empty(name)) return ResultData.form("f-1", Util.f("이름을 입력해주세요."));
		if(Util.empty(nickname)) return ResultData.form("f-1", Util.f("닉네임을 입력해주세요."));
		if(Util.empty(phoneNumber)) return ResultData.form("f-1", Util.f("전화번호를 입력해주세요."));
		
		ResultData doJoinRD = memberService.doJoin(loginId, loginPw, email, name, nickname, phoneNumber);
		
		if(doJoinRD.isFail()) {
			return doJoinRD; 
		}
		
		Member member = memberService.getMemberById((int)doJoinRD.getData());
		
		return ResultData.newData(doJoinRD, member);
	}	
}
