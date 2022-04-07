package com.nyj.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyj.exam.demo.repository.ReactionPointRepository;

@Service
public class ReactionPointService {
	@Autowired
	private ReactionPointRepository reactionPointRepository; 
	
	public boolean actorCanMakeReactionPoint(int articleId, int memberId, String relCodeType) {		
		if( memberId == 0 ) {
			return false;
		}
		return reactionPointRepository.actorCanMakeReactionPoint(articleId, memberId, relCodeType) == 0; 
	}
	
}
