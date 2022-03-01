package com.nyj.exam.demo.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nyj.exam.demo.service.ArticleService;
import com.nyj.exam.demo.service.MemberService;
import com.nyj.exam.demo.util.Util;
import com.nyj.exam.demo.vo.Article;
import com.nyj.exam.demo.vo.Member;
import com.nyj.exam.demo.vo.ResultData;
import com.nyj.exam.demo.vo.Rq; 

@Controller
public class UseMemberController { 
	
	
	@Autowired
	private MemberService memberService;

	
	@RequestMapping("/usr/member/join")
	public String showJoin() {
		return "usr/member/join";
	}
	
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(String loginId, String loginPw, String email, String name, String nickname, String phoneNumber, Model model) {

		if(Util.empty(loginId)){
			return Util.jsHistoryBack("아이디를 입력해주세요.");
		}
		
		if(Util.empty(loginPw)){
			return Util.jsHistoryBack("비밀번호를 입력해주세요.");
		}
		
		if(Util.empty(email)){
			return Util.jsHistoryBack("이메일을 입력해주세요.");
		}
		
		if(Util.empty(name))  {
			return Util.jsHistoryBack(" 이름을 입력해주세요.");
		}
		
		if(Util.empty(nickname)) {
			return Util.jsHistoryBack(" 닉네임을 입력해주세요.");
		}
		
		if(Util.empty(phoneNumber))  {
			return Util.jsHistoryBack("전화번호를 입력해주세요.");
		} 

		
		ResultData doJoinRD = memberService.doJoin(loginId, loginPw, email, name, nickname, phoneNumber);
		
		if(doJoinRD.isFail())   {
			return Util.jsHistoryBack(doJoinRD.getMsg());
		}
		
		Member member = memberService.getMemberById((int)doJoinRD.getData1());
		
		return Util.jsReplace("회원가입이 완료되었습니다", "/");
	}	
	
	// login
	@RequestMapping("usr/member/login")
	public String showLogin() {
		return "/usr/member/login";
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody 
	public Object doLogin(HttpServletRequest req, HttpSession session,String loginId, String loginPw, Model model) {
		Rq rq = (Rq)req.getAttribute("rq");
		
		if (rq.isLoginedCheck()) {
			return Util.jsHistoryBack("이미 로그인되었습니다.");
		}
		if (Util.empty(loginId)) {
			return Util.jsHistoryBack("로그인 아이디를 입력해주세요.");			
		}
		if (Util.empty(loginPw)) {
			return Util.jsHistoryBack("비밀번호를 입력해주세요.");			
		} 
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if (member == null) {
			return Util.jsHistoryBack("없는 계정입니다");
		}
		if (!member.getLoginPw().equals(loginPw)) {
			return Util.jsHistoryBack("비밀번호가 일치하지 않습니다.");	
		}
		
		rq.login(member);
		
//		return esultData.form("s-1", Util.f("%s(%s)님이 로그인 하셨습니다." , member.getNickname(),loginId), "member",member);
		return Util.jsReplace(Util.f("%s님 반갑습니다", member.getName()), "/");
	}
	

	// logout
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout(HttpServletRequest req) {
		Rq rq = (Rq)req.getAttribute("rq");
		
		rq.logout();
		
		return Util.jsReplace("", "/");
	}
	
}









