package com.nyj.exam.demo.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.nyj.exam.demo.util.Util;
import com.nyj.exam.demo.vo.ResultData;
import com.nyj.exam.demo.vo.Rq;

@Component
public class NeedLogin implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		Rq rq = (Rq) req.getAttribute("rq");

		if(rq.isLoginedCheck() == false) {
			rq.publicHistoryBackJS("로그인을 해주세요.");
			return false;
		}
		
		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}
	
}
