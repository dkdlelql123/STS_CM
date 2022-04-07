package com.nyj.exam.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ReactionPointRepository {

	@Select("""
		SELECT IFNULL(SUM(RP.point), 0) AS `extra_sumPoint`
		FROM `reactionPoint` AS RP
		WHERE RP.relTypeCode = #{relCodeType}
		AND RP.relId = #{articleId}
		AND RP.memberId = #{memberId}
	""") 
	int actorCanMakeReactionPoint(int articleId, int memberId, String relCodeType);

	
}
