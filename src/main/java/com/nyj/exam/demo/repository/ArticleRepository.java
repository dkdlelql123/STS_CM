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
	 
	public void writeArticle(@Param("title") String title,@Param("body") String body, @Param("memberId") int memberId) ;
	
	public List<Article> getArticles();

	public List<Article> getForPrintArticles();
	
	public Article getArticle(@Param("id") int id) ;

	public Article getForPrintArticle(@Param("id") int id) ;
	
	public void deleteArticle(@Param("id") int id) ;  

	public void modifyArticle(@Param("id") int id, @Param("title") String title, @Param("body") String body);

	
	public int last_insert_id();
}
