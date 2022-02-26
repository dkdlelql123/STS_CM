package com.nyj.exam.demo.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		
		return ResultData.form("s-1", Util.f("%s번째 글이 작성되었습니다",id), "article", article);	
	}
	
	@RequestMapping("/usr/articles")
	public String showArticles(Model model) {
		
		List<Article> articleList = articleService.getForPrintArticles();

		model.addAttribute("aritcles", articleList);
		
		return "usr/article/list";
	}
	
	@RequestMapping("/usr/article/detail") 
	public String showArticleDetail(HttpSession session, Model model, int id){
		boolean loginedCheck = false;
		int loginedMemberId = 0 ;
		
		if(session.getAttribute("loginedMemberId") != null) {
			loginedCheck = true;
			loginedMemberId = (int)session.getAttribute("loginedMemberId");
		} 
		
		Article article = articleService.getForPrintArticle(id);
		
		if( article == null ) {
			model.addAttribute("error", "해당 게시판을 찾을 수 없습니다.");
			return "/usr/error";
		}

		
		if( article.getMemberId() == loginedMemberId) {
			article.setActorCanModify(true);
		}
		
		model.addAttribute("article", article);
		return "/usr/article/detail";
		
	}
	
	@RequestMapping("/usr/article/delete")
	public String doDelete(HttpSession session, int id, Model model){
		boolean loginedCheck = false;
		int loginedMemberId = 0 ;
		
		if(session.getAttribute("loginedMemberId") != null) {
			loginedCheck = true;
			loginedMemberId = (int)session.getAttribute("loginedMemberId");
		} 
		
		if(loginedCheck == false) { 
			model.addAttribute("error", "로그인 먼저해주세요.");
			return "/usr/error";
		}
		
		Article article = articleService.getArticle(id);
		
		if( article == null ) { 
			model.addAttribute("error", "게시물이 없습니다.");
			return "/usr/error";
		}
		
		if( article.getMemberId() != loginedMemberId ) { 
			model.addAttribute("error", "권한이 없습니다.");
			return "/usr/error";
		}
		
		articleService.deleteArticle(id);
		return "redirect:/usr/articles";
	}
	
	
	@RequestMapping("/usr/article/modify") 
	public String doModify(HttpSession session, int id, String title, String body, Model model){
	
		boolean loginedCheck = false;
		int loginedMemberId = 0 ;
		
		if(session.getAttribute("loginedMemberId") != null) {
			loginedCheck = true;
			loginedMemberId = (int)session.getAttribute("loginedMemberId");
		} 
		
		if(loginedCheck == false) { 
			model.addAttribute("error", "로그인 먼저해주세요.");
			return "/usr/error";
		}
		
		ResultData RD = articleService.modifyCheck(id, loginedMemberId);
		
		if(RD.isFail()) { 
			model.addAttribute("error", "실패했습니다.");
			return "/usr/error";
		}
		 
		return "redirect:/usr/article/detail?id="+id;
		
	}
 
}
