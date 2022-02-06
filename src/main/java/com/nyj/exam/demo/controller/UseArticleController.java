package com.nyj.exam.demo.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nyj.exam.demo.service.ArticleService;
import com.nyj.exam.demo.vo.Article; 

@Controller
public class UseArticleController {
	
//	M
//	서비스, 레포지토리, db, data
// ☆순서 : 컨트롤러 > 서비스 > dao > DB
	
//	V
//	jsp(, js, css, html)
	
//	C
//	컨트롤러 

	@Autowired
	private ArticleService articleService; 

	// 액션 메서드	
	@RequestMapping("/usr/article/write")
	@ResponseBody
	public List<Article> writeArticle(String title, String body){ 
		 
		articleService.writeArticle(title, body);
		
		List<Article> article = articleService.getArticles();
		
		return article;
	}
	
	@RequestMapping("/usr/article")
	@ResponseBody
	public Object showDetail(int id){
		Article article = articleService.getArticle(id);
		if( article == null ) {
			return id + "번 게시물이 없습니다";
		}
		
		return article;
	}
	
	@RequestMapping("/usr/article/delete")
	@ResponseBody
	public String doDelete(int id){
		Article article = articleService.getArticle(id);
		if( article == null ) {
			return "게시물이 없습니다";
		}
		
		articleService.deleteArticle(id);
		return id+"번 게시물이 삭제완료";
	}
	
	@RequestMapping("/usr/article/modify")
	@ResponseBody
	public String doModify(int id, String title, String body){
		Article article = articleService.getArticle(id);
		if( article == null ) {
			return "게시물이 없습니다";
		}
		
		articleService.modifyArticle(id, title, body);
		return id+"번 게시물이 수정완료";
	}
 
	
}
