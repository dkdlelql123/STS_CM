package com.nyj.exam.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nyj.exam.demo.repository.ReplyRepository;
import com.nyj.exam.demo.util.Util;
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

	public List<Reply> getForPrintReplies(int relId, String relTypeCode) {
		return replyRepository.getForPrintReplies(relId, relTypeCode);
	}

}
