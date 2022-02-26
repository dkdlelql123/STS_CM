package com.nyj.exam.demo.vo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.Getter;

public class Rq {
	@Getter
	private int loginedMemberId;
	@Getter
	private boolean loginedCheck;

	public Rq(HttpServletRequest req) {
		HttpSession session = req.getSession(); 
		boolean loginedCheck = false;
		int loginedMemberId = 0 ;
		
		if(session.getAttribute("loginedMemberId") != null) {
			loginedCheck = true;
			loginedMemberId = (int)session.getAttribute("loginedMemberId");
		} 
		
		this.loginedCheck = loginedCheck;
		this.loginedMemberId = loginedMemberId;
	}

}
