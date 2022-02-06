package com.nyj.exam.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyj.exam.demo.repository.ArticleRepository;
import com.nyj.exam.demo.vo.Article;

@Service
public class ArticleService {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	// 인스턴스 변수 시작
	private List<Article> articles;
	private int id;
	
	// 생성자 
	public ArticleService() {
		id = 0;
		articles = new ArrayList<Article>();
		
		makes();
	}

	private void makes() {
		for(int a = 0; a <= 10; a++) {
			Article article = wirteArticle();
			articles.add(article);
		}
	} 
	
	// 서비스 메서드 시작
	public Article wirteArticle() {
		
		String title = "제목"+id;
		String body = "내용"+id;
		Article article = new Article(id, title, body);
		id++;
				
		return article;
	}  
	 
	public void addArticle(String title, String body) {  
		
		Article article = new Article(id, title, body);
		id++;
		articles.add(article); 
				 
	} 
	
	public Article getArticle(int id){
		for(Article article:articles) {
			if(article.getId() == id) {
				return article;
			}
		}
		return null;
	}
 
	
	public void deleteArticle(int id) {
		Article article = getArticle(id);
				
		articles.remove(article);
	}
	

	public void modifyArticle(int id, String title, String body) { 
		Article article = getArticle(id);
		article.setTitle(title);
		article.setBody(body);
	}

	public List<Article> getArticles() {
		return articles;
	}


}
