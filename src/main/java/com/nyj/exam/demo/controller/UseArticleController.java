package com.nyj.exam.demo.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nyj.exam.demo.service.ArticleService;
import com.nyj.exam.demo.service.MemberService;
import com.nyj.exam.demo.util.Util;
import com.nyj.exam.demo.vo.Article;
import com.nyj.exam.demo.vo.Member;
import com.nyj.exam.demo.vo.ResultData; 

@Controller
public class UseArticleController {

	@Autowired
	private ArticleService articleService;
	private MemberService memberService;

	// 액션 메서드	
	@RequestMapping("/usr/article/write")
	@ResponseBody
	public ResultData writeArticle(HttpSession session, String title, String body){
		boolean loginedCheck = false;
		int loginedMemberId = 0 ;
		
		if(session.getAttribute("loginedMemberId") != null) {
			loginedCheck = true;
			loginedMemberId = (int)session.getAttribute("loginedMemberId");
		} 
		
		if(loginedCheck == false) {
			return ResultData.form("f-A", "로그인 먼저해주세요.");
		}
			
		int id = articleService.writeArticle(title, body, loginedMemberId);
		
		Article article = articleService.getArticle(id);
		
		return ResultData.form("s-1", Util.f("%s번째 글이 작성되었습니다",id), article);	
	}
	
	@RequestMapping("/usr/article")
	@ResponseBody
	public ResultData showArticleDetail(int id){
		Article article = articleService.getArticle(id);
		if( article == null ) {
			return ResultData.form("f-1",  Util.f("%s번째 게시글이 없습니다.", id));
		}
		
		return ResultData.form("s-1",  "게시판을 찾았습니다.", article);
		
	}
	
	@RequestMapping("/usr/article/delete")
	@ResponseBody
	public ResultData doDelete(int id){
		Article article = articleService.getArticle(id);
		if( article == null ) {
			return ResultData.form("f-1", "게시물이 없습니다");
		}
		
		articleService.deleteArticle(id);
		
		return ResultData.form("s-1", Util.f("%s 게시물이 삭제되었습니다", id));
	}
	
	@RequestMapping("/usr/article/modify")
	@ResponseBody
	public ResultData doModify(int id, String title, String body){
		Article article = articleService.getArticle(id);
		if( article == null ) {
			return ResultData.form("f-1", "게시물이 없습니다");
		}
		
		articleService.modifyArticle(id, title, body);

		return ResultData.form("s-1", Util.f("%s 게시물이 수정되었습니다", id)); 
	}
 
	
}
