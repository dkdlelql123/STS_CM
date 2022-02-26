package com.nyj.exam.demo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

	// 인터셉터 불러오기
	 @Autowired
	 BeforeActionInterceptor beforeActionInterceptor;
	 
	 // 이 함수는 인터셉터를 적용하는 역할을 합니다.
	 @Override
	 public void addInterceptors(InterceptorRegistry registry) {
		 registry.addInterceptor(beforeActionInterceptor)
		 .addPathPatterns("/**")
		 .excludePathPatterns("/resource/**")
		 .excludePathPatterns("/error");
	 }

}
