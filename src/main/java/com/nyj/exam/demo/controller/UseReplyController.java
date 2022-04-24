package com.nyj.exam.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nyj.exam.demo.service.ReplyService;
import com.nyj.exam.demo.util.Util;
import com.nyj.exam.demo.vo.Article;
import com.nyj.exam.demo.vo.Member;
import com.nyj.exam.demo.vo.Reply;
import com.nyj.exam.demo.vo.ResultData;
import com.nyj.exam.demo.vo.Rq;

@Controller
public class UseReplyController {
	ReplyService replyService;
	private Rq rq;
	
	public UseReplyController(ReplyService replyService,Rq rq) {
		this.replyService = replyService;
		this.rq = rq;
	}
	
	@RequestMapping("/usr/reply/write")
	@ResponseBody
	public String doWrite(String body, String relTypeCode, int relId, String replaceUri){
		if(Util.empty(relTypeCode)) 
			return rq.jsHistoryBack("relTypeCode를 입력해주세요.");
		
		if(Util.empty(relId)) 
			return rq.jsHistoryBack("relId를 입력해주세요.");
		
		if(Util.empty(body)) 
			return rq.jsHistoryBack("body를 입력해주세요.");
		
		ResultData replyRd = replyService.writeReply(body, relTypeCode, relId, rq.getLoginedMemberId());
		int replyId = (int)replyRd.getData1();
		
		if(Util.empty(replaceUri)) {
			switch(relTypeCode) {
			case "article" :
				replaceUri = Util.f("/usr/article/detail?id=%d", relId);
				break;
			}
		}
		
		return rq.jsReplace(replyRd.getMsg(), replaceUri);
	}
		
	@RequestMapping("/usr/reply/modify")
	@ResponseBody
	public String doModifyReply(int id, String body, int relId) {
		replyService.doModifyReply(id, body);
		
		return rq.jsReplace("댓글이 수정이 완료되었습니다.", Util.f("/usr/article/detail?id=%d", relId));
	}
	
	@RequestMapping("/usr/reply/doDelete")
	@ResponseBody
	public String doDeleteReply(int id, int relId) {
		
		if(Util.empty(id)) {
			return rq.jsHistoryBack("id를 입력해주세요");
		}
		
		Reply reply = replyService.getForPrintReply(rq.getLoginedMember(), id);
		
		if(reply == null) {
			return rq.jsHistoryBack(Util.f("%d번 댓글은 존재하지 않습니다.", id));			
		}
		
		if(reply.isExtra_actorCanDelete() == false) {
			return rq.jsHistoryBack(Util.f("%d번 댓글을 삭제할 권한이 없습니다.", id));
		}
		
		replyService.doDeleteReply(id);
		
		return rq.jsReplace("댓글이 삭제되었습니다.", Util.f("/usr/article/detail?id=%d", relId));
	}
}
