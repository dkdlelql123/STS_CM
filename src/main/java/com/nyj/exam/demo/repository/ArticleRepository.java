package com.nyj.exam.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.nyj.exam.demo.service.ArticleService;
import com.nyj.exam.demo.vo.Article;

@Mapper
public interface ArticleRepository {
	
	// 인스턴스 변수 시작
//	private List<Article> articles;
//	private int id;

	// 생성자  
//	public ArticleRepository() {
//		id = 0;
//		articles = new ArrayList<Article>();
//		
//		makeTestData();
//
//	}
//
//	public void makeTestData() {
//		for(int a = 0; a <= 10; a++) {
//			writeArticle("제목"+a, "내용"+a); 
//		}
//		
//	}    


	// 서비스 메서드 시작    
	public void writeArticle(@Param("title") String title,@Param("body") String body) ;
	
	public Article getArticle(@Param("id") int id) ;
	
	public void deleteArticle(@Param("id") int id) ;  

	public void modifyArticle(@Param("id") int id, @Param("title") String title,@Param("body") String body);

	public List<Article> getArticles();
	
	public int last_insert_id();
}
