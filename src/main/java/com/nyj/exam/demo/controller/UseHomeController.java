package com.nyj.exam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nyj.exam.demo.vo.Rq;

@Controller
public class UseHomeController {
	
	private Rq rq;
	public UseHomeController(Rq rq) {
		this.rq = rq;
	}
	
	@RequestMapping("/usr/home/main")
	public String showMain(){
		return "/usr/home/main";
	}
	
	@RequestMapping("/")
	public String showRoot(){ 
		return "redirect:usr/home/main";
	}
	
}
