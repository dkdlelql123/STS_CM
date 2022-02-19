package com.nyj.exam.demo.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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
	
	
	// login
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public ResultData doLogin(HttpSession session,String loginId, String loginPw) {
		boolean loginCheck = false;
		
		if( session.getAttribute("MemberLoginId") != null ) {
			loginCheck = true;
		}
		
		if( loginCheck ) return ResultData.form("f-0", "이미 로그인한 상태입니다.");
		
		
		if(Util.empty(loginId))  return ResultData.form("f-1", "아이디를 입력해주세요.");
		if(Util.empty(loginPw)) return ResultData.form("f-1", Util.f("비밀번호를 입력해주세요."));
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if(member == null) {
			return ResultData.form("f-10", Util.f("%s는 존재하는 계정이 아닙니다", loginId));
		}
		
		if(!member.getLoginPw().equals(loginPw)) {
			return ResultData.form("f-10", "패스워드가 틀렸습니다");
		}
		
		session.setAttribute("MemberLoginId", member.getId());
		
		return ResultData.form("s-1", Util.f("%s(%s)님이 로그인 하셨습니다." , member.getNickname(),loginId), member);
	}
	

	// logout
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public ResultData doLogout(HttpSession session) {
		 boolean loginCheck = false;
		
		if( session.getAttribute("MemberLoginId") == null ) {
			loginCheck = true;
		}
		
		if( loginCheck ) {
			return ResultData.form("s-1", "이미 로그아웃 되셨습니다.");
		}
		
		session.removeAttribute("MemberLoginId");
		return ResultData.form("s-2", "로그아웃 되셨습니다.");
		
	}
	
}










