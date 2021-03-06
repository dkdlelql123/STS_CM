package com.nyj.exam.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.nyj.exam.demo.interceptor.BeforeActionInterceptor;
import com.nyj.exam.demo.interceptor.NeedLogin;
import com.nyj.exam.demo.interceptor.NeedLogoutInterceptor;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

	// 인터셉터 불러오기
	 @Autowired
	 BeforeActionInterceptor beforeActionInterceptor;
	 
	 @Autowired
	 NeedLogin needLogin;
	 
	 @Autowired
	 NeedLogoutInterceptor needLogoutInterceptor;
	 
	 // 이 함수는 인터셉터를 적용하는 역할을 합니다.
	 @Override
	 public void addInterceptors(InterceptorRegistry registry) {
		 registry.addInterceptor(beforeActionInterceptor)
		 .addPathPatterns("/**")
		 .excludePathPatterns("/resource/**")
		 .excludePathPatterns("/error");
		 
		 registry.addInterceptor(needLogin)
		 .addPathPatterns("/usr/member/mypage")
		 .addPathPatterns("/usr/member/checkPassword")
		 .addPathPatterns("/usr/member/doCheckPassword")
		 .addPathPatterns("/usr/member/modify")
		 .addPathPatterns("/usr/member/doModify")
		 .addPathPatterns("/usr/article/write")
		 .addPathPatterns("/usr/article/doWrite")
		 .addPathPatterns("/usr/article/modify")
		 .addPathPatterns("/usr/article/doModify")
		 .addPathPatterns("/usr/article/doDelete")
		 .addPathPatterns("/usr/reply/write")
		 .addPathPatterns("/usr/reply/modify")
		 .addPathPatterns("/usr/reply/doModify")
		 .addPathPatterns("/usr/reply/doDelete")
		 .addPathPatterns("/usr/reactionPoint/doGooddReaction")
		 .addPathPatterns("/usr/reactionPoint/doBadReaction")
		 .addPathPatterns("/usr/reactionPoint/doCancleGoodReaction")
		 .addPathPatterns("/usr/reactionPoint/doCancleBadReaction")		 
		 ;
		 
		 registry.addInterceptor(needLogoutInterceptor)
		 .addPathPatterns("/usr/member/login")
		 .addPathPatterns("/usr/member/doLogin")
		 .addPathPatterns("/usr/member/join")
		 .addPathPatterns("/usr/member/doJoin")
		 .addPathPatterns("/usr/member/findLoginId")
		 .addPathPatterns("/usr/member/doFindLoginId")
		 .addPathPatterns("/usr/member/findLoginPw")
		 .addPathPatterns("/usr/member/doFindLoginPw")
		 ;
	 }

}
