package com.nyj.exam.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.nyj.exam.demo.vo.Reply;

@Mapper
public interface ReplyRepository {

	@Insert("""
			INSERT INTO reply
			SET regDate = NOW(),
			updateDate = NOW(),
			memberId = #{memberId},
			relTypeCode = #{relTypeCode},
			relId = #{relId},
			`body` = #{body}
			""")
	void writeReply(String body, String relTypeCode, int relId, int memberId);

	@Select("""
			SELECT last_insert_id()
			""")
	int last_insert_id();
	
	@Select("""
			SELECT r.*,
			m.nickname AS extra_actorName
			FROM `member` m
			LEFT JOIN reply r
			ON m.id = r.memberId
			WHERE r.relTypeCode = #{relTypeCode}
			AND r.relId = #{relId}
			ORDER BY r.regDate ; 
			""") 
	List<Reply> getForPrintReplies(int relId,String relTypeCode);
}
