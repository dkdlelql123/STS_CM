package com.nyj.exam.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nyj.exam.demo.repository.ReplyRepository;
import com.nyj.exam.demo.util.Util;
import com.nyj.exam.demo.vo.Member;
import com.nyj.exam.demo.vo.Reply;
import com.nyj.exam.demo.vo.ResultData;

@Service
public class ReplyService {
	ReplyRepository replyRepository;
	
	public ReplyService(ReplyRepository replyRepository) {
		this.replyRepository = replyRepository;
	}
	
	public ResultData writeReply(String body, String relTypeCode, int relId, int memberId) {
		replyRepository.writeReply(body, relTypeCode, relId, memberId);
		int id = replyRepository.last_insert_id();
		return ResultData.form("s-1", Util.f("%d 댓글이 작성되었습니다", id) ,"reply",id); 
	}

	public List<Reply> getForPrintReplies(Member member,int relId, String relTypeCode) {
		List<Reply> replies = replyRepository.getForPrintReplies(relId, relTypeCode);
		
		for(Reply reply : replies) {
			ResultData modifyRd = actorModifyReply(member, reply);
			reply.setExtra_actorCanModify(modifyRd.isSuccess());
			
			ResultData deleteRd = actorDeleteReply(member, reply);
			reply.setExtra_actorCanDelete(deleteRd.isSuccess());
		}
		
		return replies;
	}
	
	public void updateForPrintData(Member member, Reply reply) {
		if (reply == null) {
			return;
		}
		ResultData modifyRd = actorModifyReply(member, reply);
		reply.setExtra_actorCanModify(modifyRd.isSuccess());
		
		ResultData deleteRd = actorDeleteReply(member, reply);
		reply.setExtra_actorCanDelete(deleteRd.isSuccess());
	}

	public ResultData actorModifyReply(Member member, Reply reply) {
		if(member == null) {
			return ResultData.form("f-1","댓글이 없습니다.");
		}
		
		if(member.getId() != reply.getMemberId()) {
			return ResultData.form("f-1","권한이 없습니다.");
		}
		return ResultData.form("s-1","댓글 수정이 가능합니다.");
	}

	public ResultData actorDeleteReply(Member member, Reply reply) {
		if(member == null) {
			return ResultData.form("f-1","댓글이 없습니다.");
		}
		
		if(member.getId() != reply.getMemberId()) {
			return ResultData.form("f-1","권한이 없습니다.");
		}
		return ResultData.form("s-1","댓글 삭제가 가능합니다.");
	}

	public Reply getForPrintReply(Member member,int id) {
		Reply reply = replyRepository.getForPrintReply(id);
		
		updateForPrintData(member, reply);
		
		return reply;
	}
	
	public void doModifyReply(int id, String body) {
		replyRepository.doModifyReply(id, body);
	}

	public void doDeleteReply(int id) {
		replyRepository.doDeleteReply(id);
	}
	
}
