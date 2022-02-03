package com.nyj.exam.demo.controller;

import java.lang.ProcessBuilder.Redirect;
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
	public Article doAdd(){ 
		id++;
		String title = "제목"+id;
		String body = "내용"+id;
		Article article = new Article(id, title, body);
		
		lists.add(article); 
		
		return article;
	} 
 
	@RequestMapping("/usr/articles")
	@ResponseBody
	public List<Article> showArticle(){ 
		
		makes();
		 
		return lists;
	}
	
	private void makes() {
		for(int a = 0; a <= 10; a++) {
			id = a;
			String title = "제목"+a;
			String body = "내용"+a;
			Article article = new Article(id, title, body);
			
			lists.add(article);
		}
	} 
	
	
}
