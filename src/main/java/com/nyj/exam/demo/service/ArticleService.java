package com.nyj.exam.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyj.exam.demo.repository.ArticleRepository;
import com.nyj.exam.demo.repository.MemberRepository;
import com.nyj.exam.demo.vo.Article;

@Service
public class ArticleService {  
//	@Autowired
	private ArticleRepository articleRepository;
	
	// articleRepository 보다 조금 더 빠르게 진행됨.
	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository; 
	} 

	public int writeArticle(String title, String body) {
		articleRepository.writeArticle(title, body);
		return articleRepository.last_insert_id();
	}

	public List<Article> getArticles() {	
		return articleRepository.getArticles();
	}

	public Article getArticle(int id) {
		return articleRepository.getArticle(id);
	}

	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
	}

	public void modifyArticle(int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);
		
	}
	
	
}
