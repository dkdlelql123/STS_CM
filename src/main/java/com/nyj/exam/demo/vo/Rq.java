package com.nyj.exam.demo.vo;

import java.io.IOException;
import java.net.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nyj.exam.demo.service.MemberService;
import com.nyj.exam.demo.util.Util;

import lombok.Getter;

public class Rq {
	@Getter
	private int loginedMemberId;
	@Getter
	private boolean loginedCheck;
	@Getter
	private Member loginedMember;
	
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private HttpSession session;

	public Rq(HttpServletRequest req, HttpServletResponse resp, MemberService memberService) {
		this.req = req;
		this.resp = resp;
		
		this.session = req.getSession(); 
		
		boolean loginedCheck = false;
		int loginedMemberId = 0 ;
		Member loginedMember = null;
		
		if(session.getAttribute("loginedMemberId") != null) {
			loginedCheck = true;
			loginedMemberId = (int)session.getAttribute("loginedMemberId");
			loginedMember = memberService.getMemberById(loginedMemberId);
		} 
		
		this.loginedCheck = loginedCheck;
		this.loginedMemberId = loginedMemberId;
		this.loginedMember = loginedMember;
	}

	public void publicHistoryBackJS(String msg) {
		resp.setContentType("text/html; charset=UTF-8");
		println("<script>");
		if(!Util.empty(msg)) {
			println("alert( '"+msg+"' );");
		}
		println("history.back();");
		println("</script>");
	}
	
	public void print(String str) {
		try {			
			resp.getWriter().append(str);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void println(String str) {
		print(str+"\n ");
	}

	public void login(Member member) {
		session.setAttribute("loginedMemberId", member.getId());
		
	}

	public void logout() {
		session.removeAttribute("loginedMemberId");
	}

	public String historyBackOnView(String msg) {
	
		req.setAttribute("msg", msg);
		req.setAttribute("historyBack", true);
		
		return "/usr/common/js";
	}

	public String jsHistoryBack(String msg) {
		return Util.jsHistoryBack(msg);
	}

	public String jsReplace(String msg, String uri) {
		return Util.jsReplace(msg, uri);
	}

}
