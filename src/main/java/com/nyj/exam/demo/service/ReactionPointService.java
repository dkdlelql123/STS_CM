package com.nyj.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyj.exam.demo.repository.ArticleRepository;
import com.nyj.exam.demo.repository.ReactionPointRepository;
import com.nyj.exam.demo.vo.ResultData;
import com.nyj.exam.demo.vo.Rq;

@Service
public class ReactionPointService {
	private ReactionPointRepository reactionPointRepository;
	private	ArticleService articleService;
		
	public ReactionPointService(ReactionPointRepository reactionPointRepository, ArticleService articleService) {
		this.reactionPointRepository = reactionPointRepository; 
		this.articleService= articleService;
	}
	
	public ResultData actorCanMakeReactionPoint(int articleId, int memberId, String relCodeType) {		
		if( memberId == 0 ) {
			return ResultData.form("f-1", "로그인 이후 이용해주세요");
		}
		
		int getSumReactionPointByMemberId = reactionPointRepository.actorCanMakeReactionPoint(articleId, memberId, relCodeType);
		
		if( getSumReactionPointByMemberId != 0) {
			return ResultData.form("f-2", "이미 리액션처리 되었습니다.", "getSumReactionPointByMemberId", getSumReactionPointByMemberId);
		} 
		return ResultData.form("s-1", "리액션 가능합니다.", "getSumReactionPointByMemberId", getSumReactionPointByMemberId);
	}

	public ResultData addGoodReactionPoint(int relId, int loginedMemberId, String relTypeCode) {
		reactionPointRepository.addGoodReactionPoint(relId,loginedMemberId,relTypeCode); 
		
		switch(relTypeCode) {
		case "article":
			articleService.increaseGoodPoint(relId);
			break;	
		}
		
		return ResultData.form("s-1", "좋아요 처리가 완료되었습니다.");
				
	} 
	public ResultData addBadReactionPoint(int relId, int loginedMemberId, String relTypeCode) {
		reactionPointRepository.addBadReactionPoint(relId,loginedMemberId,relTypeCode); 
		
		switch(relTypeCode) {
		case "article":
			articleService.increaseBadPoint(relId);
			break;				
		}
		
		return ResultData.form("s-1", "싫어요 처리가 완료되었습니다.");
	}

	public ResultData deleteGoodReactionPoint(int relId, int loginedMemberId, String relTypeCode) {
		reactionPointRepository.deleteReactionPoint(relId,loginedMemberId,relTypeCode); 
		
		switch(relTypeCode) {
		case "article":
			articleService.decreaseGoodPoint(relId);
			break;	
		}
		
		return ResultData.form("s-1", "좋아요 취소처리가 완료되었습니다.");
	} 
	
	public ResultData deleteBadReactionPoint(int relId, int loginedMemberId, String relTypeCode) {
		reactionPointRepository.deleteReactionPoint(relId,loginedMemberId,relTypeCode); 
		
		switch(relTypeCode) {
		case "article":
			articleService.decreaseBadPoint(relId);
			break;	
		}
		
		return ResultData.form("s-1", "싫어요 취소처리가 완료되었습니다.");
	} 
	
	
	
}
