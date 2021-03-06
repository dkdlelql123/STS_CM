package com.nyj.exam.demo.vo;

import java.io.IOException;
import java.net.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.nyj.exam.demo.service.MemberService;
import com.nyj.exam.demo.util.Util;

import lombok.Getter;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
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
		
		this.loginedMemberId = loginedMemberId;
		this.loginedCheck = loginedCheck;
		this.loginedMember = loginedMember;
		
		this.req.setAttribute("rq", this);
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
	
	public boolean isNotLogin() {
		return !loginedCheck;
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

	public String getCurrentUri() {
		String currentUri = req.getRequestURI();
		String queryString = req.getQueryString();
		
		if(currentUri != null && queryString.length() > 0) {
			currentUri +=  "?" + queryString;
		}
		
		return currentUri;
	}
	
	public String getEncodedCurrentUri() {
		return Util.getUriEncoded(getCurrentUri());
	}
}










