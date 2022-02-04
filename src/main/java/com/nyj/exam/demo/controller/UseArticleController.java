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
	
	// 인스턴스 변수 시작
	private List<Article> lists;
	private int id;
	
	public UseArticleController() {
		id = 0;
		lists = new ArrayList<Article>();
	}
	
	
	// 생성자
	public Article wirteArticle() {
		
		String title = "제목"+id;
		String body = "내용"+id;
		Article article = new Article(id, title, body);
		id++;
				
		return article;
	}
	
 
	// 서비스 메서드 시작
	private void makes() {
		for(int a = 0; a <= 10; a++) {
			Article article = wirteArticle();
			lists.add(article);
		}
	} 
	
	public Article getArticle(int id){
		for(Article list:lists) {
			if(list.getId() == id) {
				return list;
			}
		}
		return null;
	}
	
	private void deleteArticle(int id) {
		Article article = getArticle(id);
				
		lists.remove(article);
	}
	

	private void modifyArticle(int id, String title, String body) { 
		Article article = getArticle(id);
		article.setTitle(title);
		article.setBody(body);
	}
	
	

	// 액션 메서드
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(){ 
		Article article = wirteArticle();
		
		return article;
	} 
	
	@RequestMapping("/usr/articles/add")
	@ResponseBody
	public List<Article> addArticles(){ 
		
		makes();
		return lists;
	}
 
	@RequestMapping("/usr/articles")
	@ResponseBody
	public List<Article> showArticle(){ 
		 
		return lists;
	}
	
	@RequestMapping("/usr/article")
	@ResponseBody
	public Object showDetail(int id){
		Article article = getArticle(id);
		if( article == null ) {
			return id + "번 게시물이 없습니다";
		}
		
		return article;
	}
	
	@RequestMapping("/usr/article/delete")
	@ResponseBody
	public String doDelete(int id){
		Article article = getArticle(id);
		if( article == null ) {
			return "게시물이 없습니다";
		}
		
		deleteArticle(id);
		return id+"번 게시물이 삭제완료";
	}
	
	@RequestMapping("/usr/article/modify")
	@ResponseBody
	public String doModify(int id, String title, String body){
		Article article = getArticle(id);
		if( article == null ) {
			return "게시물이 없습니다";
		}
		
		modifyArticle(id, title, body);
		return id+"번 게시물이 수정완료";
	}


	
	
	
}
