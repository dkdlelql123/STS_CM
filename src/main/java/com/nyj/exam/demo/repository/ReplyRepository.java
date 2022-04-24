package com.nyj.exam.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
	
	@Update("""
			Update reply
			SET `body` =  #{body},
			updateDate = NOW()
			WHERE id = #{id}
			""")
	public void doModifyReply(int id, String body);

	@Delete("""
			DELETE FROM reply
			WHERE id = #{id}
			""")
	public void doDeleteReply(int id);
	
	@Select("""
			SELECT r.*,
			m.nickname AS extra_actorName
			FROM `member` m
			LEFT JOIN reply r
			ON m.id = r.memberId
			WHERE r.id = #{relId}
			""")
	Reply getForPrintReply(int id); 

	@Select("""
			SELECT *
			FROM reply 
			WHERE id = #{relId}  
			""") 
	Reply getReply(int id);
}
