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
import com.nyj.exam.demo.util.Util;
import com.nyj.exam.demo.vo.Article;
import com.nyj.exam.demo.vo.Board; 
import com.nyj.exam.demo.vo.ResultData;
import com.nyj.exam.demo.vo.Rq; 

@Controller
public class UseReactionPointController { 
	private Rq rq;
	private ReactionPointService reactionPointService;
	
	public UseReactionPointController(ReactionPointService reactionPointService, Rq rq) { 
		this.reactionPointService = reactionPointService;
		this.rq = rq;
	}

	@RequestMapping("/usr/reactionPoint/doGoodReaction")
	@ResponseBody
	String doGoodReaction(String relTypeCode, int relId, String replaceUri) {
		ResultData reactionCheckRd = reactionPointService.actorCanMakeReactionPoint(relId, rq.getLoginedMemberId() ,relTypeCode);
		
		if( reactionCheckRd.isFail() ) {
			return rq.jsHistoryBack( reactionCheckRd.getMsg() );
		}
		
		ResultData addGoodReactionPointRd = reactionPointService.addGoodReactionPoint(relId, rq.getLoginedMemberId() ,relTypeCode);

		return rq.jsReplace(addGoodReactionPointRd.getMsg(), replaceUri);
	}
	
	@RequestMapping("/usr/reactionPoint/doBadReaction")
	@ResponseBody
	String doBadReaction(String relTypeCode, int relId, String replaceUri) {
		ResultData reactionCheckRd = reactionPointService.actorCanMakeReactionPoint(relId, rq.getLoginedMemberId() ,relTypeCode);
		
		if( reactionCheckRd.isFail() ) {
			return rq.jsHistoryBack( reactionCheckRd.getMsg() );
		}
		
		ResultData addBadReactionPointRd = reactionPointService.addBadReactionPoint(relId, rq.getLoginedMemberId() ,relTypeCode);

		return rq.jsReplace(addBadReactionPointRd.getMsg(), replaceUri);
	}
	
	@RequestMapping("/usr/reactionPoint/doCancleGoodReaction")
	@ResponseBody
	String doCancleGoodReaction(String relTypeCode, int relId, String replaceUri) {
		ResultData reactionCheckRd = reactionPointService.actorCanMakeReactionPoint(relId, rq.getLoginedMemberId() ,relTypeCode);
		
		if( reactionCheckRd.isSuccess() ) {
			return rq.jsHistoryBack( reactionCheckRd.getMsg() );
		}
		
		ResultData deleteGoodReactionPointRd = reactionPointService.deleteGoodReactionPoint(relId, rq.getLoginedMemberId() ,relTypeCode);

		return rq.jsReplace(deleteGoodReactionPointRd.getMsg(), replaceUri);
	}
	
	@RequestMapping("/usr/reactionPoint/doCancleBadReaction")
	@ResponseBody
	String doCancleBadReaction(String relTypeCode, int relId, String replaceUri) {
		ResultData reactionCheckRd = reactionPointService.actorCanMakeReactionPoint(relId, rq.getLoginedMemberId() ,relTypeCode);
		
		if( reactionCheckRd.isSuccess() ) {
			return rq.jsHistoryBack( reactionCheckRd.getMsg() );
		}
		
		ResultData deleteBadReactionPointRd = reactionPointService.deleteBadReactionPoint(relId, rq.getLoginedMemberId() ,relTypeCode);

		return rq.jsReplace(deleteBadReactionPointRd.getMsg(), replaceUri);
	}
	
 
}
