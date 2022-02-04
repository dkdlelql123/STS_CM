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
	
	public Article wirteArticle() {
		
		String title = "제목"+id;
		String body = "내용"+id;
		Article article = new Article(id, title, body);
		id++;
				
		return article;
	}
 
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(){ 
		Article article = wirteArticle();
		
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
			Article article = wirteArticle();
			lists.add(article);
		}
	} 
	
	
}
