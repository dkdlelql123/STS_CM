package com.nyj.exam.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.nyj.exam.demo.service.ArticleService;
import com.nyj.exam.demo.vo.Article;

@Component
public class ArticleRepository {
	
	// 인스턴스 변수 시작
	private List<Article> articles;
	private int id;

	// 생성자  
	public ArticleRepository() {
		id = 0;
		articles = new ArrayList<Article>();
		
		makeTestData();

	}

	public void makeTestData() {
		for(int a = 0; a <= 10; a++) {
			writeArticle("제목"+a, "내용"+a); 
		}
		
	}    


	// 서비스 메서드 시작    
	public Article writeArticle(String title, String body) {   
		Article article = new Article(id, title, body);
		
		id++;
		articles.add(article);
		
		return article;
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
