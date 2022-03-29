package com.nyj.exam.demo.controller;
 
import java.util.List;

import javax.servlet.http.HttpServletRequest; 
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nyj.exam.demo.service.ArticleService;
import com.nyj.exam.demo.service.BoardService;
import com.nyj.exam.demo.service.MemberService;
import com.nyj.exam.demo.util.Util;
import com.nyj.exam.demo.vo.Article;
import com.nyj.exam.demo.vo.Board; 
import com.nyj.exam.demo.vo.ResultData;
import com.nyj.exam.demo.vo.Rq; 

@Controller
public class UseArticleController {

	private ArticleService articleService;
	private MemberService memberService;
	private BoardService boardService;
	private Rq rq;
	
	public UseArticleController(ArticleService articleService, MemberService memberService, BoardService boardService, Rq rq) {
		this.articleService = articleService;
		this.memberService = memberService;
		this.boardService = boardService;
		this.rq = rq;
	}

	// 액션 메서드	
	@RequestMapping("/usr/articles")
	public String showArticles(Model model) {
		List<Article> articleList = articleService.getForPrintArticles();
		
		model.addAttribute("aritcles", articleList);
		
		return "usr/article/list";
	}
	
	@RequestMapping("/usr/article/list")
	public String showArticle(Model model, int boardId) {
		Board board = boardService.findById(boardId);
		
		if(board == null) {
			return rq.historyBackOnView(Util.f("%d번 게시판은 존재하지 않습니다.", boardId));
		}
		
		int articleCount = articleService.getArticleListCount(boardId);
		List<Article> articleList = articleService.getForPrintArticlelist(boardId);
		
		model.addAttribute("board", board);
		model.addAttribute("articleCount", articleCount);
		model.addAttribute("aritcles", articleList);
		
		return "usr/article/list";
	}
	
	@RequestMapping("/usr/article/write")
	public String showWrite() {
		return "usr/article/write";
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public String writeArticle(String title, String body){
		int id = articleService.writeArticle(title, body, rq.getLoginedMemberId());
		
		Article article = articleService.getArticle(id);
		
//		return ResultData.form("s-1", , "article", article);	
		return rq.jsReplace(Util.f("%s번째 글이 작성되었습니다",id), Util.f("/usr/article/detail?id=%d",id));
	}
	
	@RequestMapping("/usr/article/detail") 
	public String showArticleDetail(Model model, int id){
		Article article = articleService.getForPrintArticle(id);
		
		if( article == null ) {
			model.addAttribute("error", "해당 게시판을 찾을 수 없습니다.");
			return "/usr/error";
		}
		
		if( article.getMemberId() == rq.getLoginedMemberId() ) {
			article.setExtra_actorCanModify(true);
		}
		
		model.addAttribute("article", article);
		
		return "/usr/article/detail" ;
		
	}
	
	@RequestMapping("/usr/article/modify")
	public String showModify(int id, Model model) {
		Article article = articleService.getArticle(id);
		
		if( article == null ) { 
			return rq.historyBackOnView(Util.f("%d번의 게시물이 없습니다.", id));
		}
		
		ResultData rs = articleService.modifyCheck(id,rq.getLoginedMemberId());
		
		if( rs.isFail() ) { 
			return rq.historyBackOnView(rs.getMsg());
		}
		
		model.addAttribute("article", article);
		
		return "usr/article/modify";
	}
	
	@RequestMapping("/usr/article/doDelete")
	public String doDelete(int id){
		articleService.deleteArticle(id);
		
		return "redirect:/usr/articles";
	}
	
	
	@RequestMapping("/usr/article/doModify") 
	@ResponseBody
	public String doModify(int id, String title, String body, Model model){
		Article article = articleService.getArticle(id);
		
		if( article == null ) { 
			return rq.jsHistoryBack(Util.f("%d번의 게시물 없습니다.", id));
		}
		
		ResultData actorCanModify = articleService.modifyCheck(id,rq.getLoginedMemberId());
		
		if( actorCanModify.isFail() ) { 
			return rq.jsHistoryBack(actorCanModify.getMsg());
		}
		
		articleService.modifyArticle(id, title, body);
		 
		return rq.jsReplace("수정이 완료되었습니다.", "/usr/article/modify?id="+id);
		
	}
 
}
