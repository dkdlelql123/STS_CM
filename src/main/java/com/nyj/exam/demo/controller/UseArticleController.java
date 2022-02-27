package com.nyj.exam.demo.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.nyj.exam.demo.vo.Rq; 

@Controller
public class UseArticleController {

	@Autowired
	private ArticleService articleService;
	private MemberService memberService;

	// 액션 메서드	
	@RequestMapping("/usr/articles")
	public String showArticles(Model model) {
		
		List<Article> articleList = articleService.getForPrintArticles();
		
		model.addAttribute("aritcles", articleList);
		
		return "usr/article/list";
	}
	
	@RequestMapping("/usr/article/write")
	public String showWrite() {
		return "usr/article/write";
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public ResultData writeArticle(HttpServletRequest req, String title, String body){
		Rq rq = (Rq)req.getAttribute("rq");
			
		int id = articleService.writeArticle(title, body, rq.getLoginedMemberId());
		
		Article article = articleService.getArticle(id);
		
		return ResultData.form("s-1", Util.f("%s번째 글이 작성되었습니다",id), "article", article);	
	}
	
	@RequestMapping("/usr/article/detail") 
	public String showArticleDetail(HttpServletRequest req, Model model, int id){
		Rq rq = (Rq)req.getAttribute("rq");
		
		Article article = articleService.getForPrintArticle(id);
		
		if( article == null ) {
			model.addAttribute("error", "해당 게시판을 찾을 수 없습니다.");
			return "/usr/error";
		}
		
		if( article.getMemberId() == rq.getLoginedMemberId() ) {
			article.setActorCanModify(true);
		}
		
		model.addAttribute("article", article);
		
		return "/usr/article/detail" ;
		
	}
	
	@RequestMapping("/usr/article/modify")
	public String showModify(HttpServletRequest req, int id, Model model) {
		
		Rq rq = (Rq)req.getAttribute("rq"); 
		
		Article article = articleService.getArticle(id);
		
		if( article == null ) { 
			model.addAttribute("error", "게시물이 없습니다.");
			return "/usr/error";
		}
		
		model.addAttribute("article", article);
		
//		if( article.getMemberId() != rq.getLoginedMemberId() ) { 
//			model.addAttribute("error", "권한이 없습니다.");
//			return "/usr/error";
//		}
		
		return "usr/article/modify";
	}
	
	@RequestMapping("/usr/article/doDelete")
	public String doDelete(int id){
		
		articleService.deleteArticle(id);
		
		return "redirect:/usr/articles";
	}
	
	
	@RequestMapping("/usr/article/doModify") 
	public String doModify(HttpServletRequest req, int id, String title, String body, Model model){
//		Rq rq = (Rq)req.getAttribute("rq"); 
//		
//		ResultData RD = articleService.modifyCheck(id, rq.getLoginedMemberId());
//		
//		if(RD.isFail()) { 
//			model.addAttribute("error", "실패했습니다.");
//			return "/usr/error";
//		}
		
		articleService.modifyArticle(id, title, body);
		 
		return "redirect:/usr/article/detail?id="+id;
		
	}
 
}
