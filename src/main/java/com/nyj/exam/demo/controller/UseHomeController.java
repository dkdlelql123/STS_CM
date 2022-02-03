package com.nyj.exam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UseHomeController {

	private int num;
	
	public UseHomeController() {
		num = 0;
	}
	
	@RequestMapping("/usr/home/main")
	@ResponseBody
	public String showMain(){ 
		return "hello";
	}
	
	@RequestMapping("/usr/home/main4")
	@ResponseBody
	public int showCount(){
		
		num--;
		
		return num;
	} 
	
}
