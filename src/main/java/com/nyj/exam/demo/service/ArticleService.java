package com.nyj.exam.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyj.exam.demo.repository.ArticleRepository;
import com.nyj.exam.demo.repository.MemberRepository;
import com.nyj.exam.demo.util.Util;
import com.nyj.exam.demo.vo.Article;
import com.nyj.exam.demo.vo.ResultData;

@Service
public class ArticleService {  
//	@Autowired
	private ArticleRepository articleRepository;
	
	// articleRepository 보다 조금 더 빠르게 진행됨.
	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository; 
	} 

	public int writeArticle(String title, String body, int memberId) {
		articleRepository.writeArticle(title, body, memberId);
		return articleRepository.last_insert_id();
	}
	
	public List<Article> getForPrintArticles() {	
		return articleRepository.getForPrintArticles();
	}

	public List<Article> getArticles() {	
		return articleRepository.getArticles();
	}
	
	public Article getForPrintArticle(int id) {
		return articleRepository.getForPrintArticle(id);
	}

	public Article getArticle(int id) {
		return articleRepository.getArticle(id);
	}

	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
	}

	public ResultData modifyArticle(int id, String title, String body) {
		
		articleRepository.modifyArticle(id, title, body);
		
		Article article = getArticle(id);
		
		return ResultData.form("s-1", Util.f("%s 게시물이 수정되었습니다", id),  "article", article); 
		
	}

	public ResultData modifyCheck(int id, int loginedMemberId) {
		Article article = getArticle(id);

		if( article == null ) {
			return ResultData.form("f-1", "게시물이 없습니다");
		}
		
		if( article.getMemberId() != loginedMemberId) {
			return ResultData.form("f-2", "권한이 없습니다");
		}
		
		return ResultData.form("s-1", "수정 가능합니다", "article" , article); 
	}
	
	
}
