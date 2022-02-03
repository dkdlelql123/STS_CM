package com.nyj.exam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UseHomeController {
	@RequestMapping("/usr/home/main")
	@ResponseBody
	public String showMain(){
		return "hello!!";
	}
	
	@RequestMapping("/usr/home/main2")
	@ResponseBody
	public String showMain2(){
		return "hello!!2!";
	}
	
	@RequestMapping("/usr/home/main3")
	@ResponseBody
	public String showMain3(){
		return "hello!!2233333222!";
	}
	
}
