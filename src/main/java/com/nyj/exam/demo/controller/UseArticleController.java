package com.nyj.exam.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nyj.exam.demo.vo.Article; 

@Controller
public class UseArticleController {
	
	private List<Article> lists;
	private int id;
	
	public UseArticleController() {
		id = 0;
		lists = new ArrayList<Article>();
	}
 
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title, String body){
		Article article = new Article(id, title, body);
		id++;
		
		lists.add(article);
		
		return article;
	} 
 
	@RequestMapping("/usr/articles")
	@ResponseBody
	public List<Article> showArticle(){ 
		
		return lists;
	}
	
}
