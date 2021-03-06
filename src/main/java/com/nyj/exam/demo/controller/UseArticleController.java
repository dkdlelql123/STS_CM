package com.nyj.exam.demo.controller;
 
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nyj.exam.demo.service.ArticleService;
import com.nyj.exam.demo.service.BoardService;
import com.nyj.exam.demo.service.MemberService;
import com.nyj.exam.demo.service.ReactionPointService;
import com.nyj.exam.demo.service.ReplyService;
import com.nyj.exam.demo.util.Util;
import com.nyj.exam.demo.vo.Article;
import com.nyj.exam.demo.vo.Board;
import com.nyj.exam.demo.vo.Reply;
import com.nyj.exam.demo.vo.ResultData;
import com.nyj.exam.demo.vo.Rq; 

@Controller
public class UseArticleController {

	private ArticleService articleService;
	private MemberService memberService;
	private BoardService boardService;
	private Rq rq;
	private ReactionPointService reactionPointService;
	private ReplyService replyService;
	
	public UseArticleController(ArticleService articleService, MemberService memberService, BoardService boardService, ReactionPointService reactionPointService, ReplyService replyService, Rq rq) {
		this.articleService = articleService;
		this.memberService = memberService;
		this.boardService = boardService;
		this.reactionPointService = reactionPointService;
		this.replyService = replyService;
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
	public String showArticle(Model model,
			@RequestParam(defaultValue = "1") int boardId, 
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int limit, 
			@RequestParam(defaultValue = "title,body") String searchType,
			@RequestParam(defaultValue = "") String searchKeyword) {
		Board board = boardService.findById(boardId);
		
		if(board == null) {
			return rq.historyBackOnView(Util.f("%d번 게시판은 존재하지 않습니다.", boardId));
		}
		
		int articleCount = articleService.getArticleListCount(boardId,searchType, searchKeyword);

		int limitStart = ((int)page - 1) * limit;
		int limitCount = limit;
		
		int totalPageLimit = (articleCount/limit) + 1; 
		
		List<Article> articleList = articleService.getForPrintArticlelist(boardId, limitStart, limitCount, searchType, searchKeyword );
		
		model.addAttribute("board", board);
		model.addAttribute("articleCount", articleCount);
		model.addAttribute("page", page); 
		model.addAttribute("end", totalPageLimit); 
		model.addAttribute("aritcles", articleList);
		
		return "usr/article/list";
	}
	
	@RequestMapping("/usr/article/write")
	public String showWrite(Model model) {
		
		ArrayList<Board> board = boardService.findAll();
		model.addAttribute("board", board);
		
		return "usr/article/write";
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public String writeArticle(String title, String body, int boardId){
		int id = articleService.writeArticle(title, body, boardId, rq.getLoginedMemberId());
		
		Article article = articleService.getArticle(id);
		
		return rq.jsReplace(Util.f("%s번째 글이 작성되었습니다",id), Util.f("/usr/article/detail?id=%d",id));
	}
	
	@RequestMapping("/usr/article/detail") 
	public String showArticleDetail(Model model, int id){
		Article article = articleService.getForPrintArticle(id);
		
		model.addAttribute("article", article);
		
		if( article.getMemberId() == rq.getLoginedMemberId() ) {
			article.setExtra_actorCanModify(true);
		}
		
		List<Reply> replies = replyService.getForPrintReplies(rq.getLoginedMember() ,id, "article");
		int repliesCount = replies.size();
		
		model.addAttribute("repliesCount",repliesCount);
		model.addAttribute("replies",replies);
		
		ResultData actorCanMakeReactionPointRD = reactionPointService.actorCanMakeReactionPoint(id, rq.getLoginedMemberId(), "article");
		
		model.addAttribute("actorCanMakeReactionPoint", actorCanMakeReactionPointRD.isSuccess());
		
		if( actorCanMakeReactionPointRD.getResultCode().equals("f-2") ) {
			if( (int)actorCanMakeReactionPointRD.getData1() > 0) {
				model.addAttribute("actorCanCancleGoodReactionPoint", true);
			} else {
				model.addAttribute("actorCanCancleBadReactionPoint", true);
			}
		}
		
		return "/usr/article/detail" ;
	}
	@RequestMapping("/usr/article/doIncreasedHit")
	@ResponseBody
	public ResultData doIncreasedHit(int id) {
		ResultData hitIncreasedRd = articleService.hitIncreased(id);
		
		if( hitIncreasedRd.isFail() ) {
			return hitIncreasedRd;
		}
		
		ResultData rd = ResultData.newData(hitIncreasedRd, "hitCount", articleService.findHitCount(id));
		
		rd.setData2("id", id);
		
		return rd;
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
		
		ArrayList<Board> board = boardService.findAll();
		model.addAttribute("board", board);
		
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
