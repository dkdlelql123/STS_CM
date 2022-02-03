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
	
	@RequestMapping("/usr/home/main5")
	@ResponseBody
	public int showResetCount(){
		
		num = 0;
		
		return num;
	} 

	@RequestMapping("/usr/home/doSetCount")
	@ResponseBody
	public String doSetCount(int count){
		this.num = count;
		return "초기화 됨 : " + num;
	} 
}
