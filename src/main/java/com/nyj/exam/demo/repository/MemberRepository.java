package com.nyj.exam.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.nyj.exam.demo.service.ArticleService;
import com.nyj.exam.demo.vo.Article;
import com.nyj.exam.demo.vo.Member;

@Mapper
public interface MemberRepository {

	public void doJoin(@Param("loginId") String loginId, @Param("loginPw") String loginPw, @Param("email") String email,
			@Param("name") String name, @Param("nickname") String nickname, @Param("phoneNumber") String phoneNumber);

	public Member getMemberById(@Param("id") int id);

	public int last_insert_id();

	public Member getMemberByLoginId(@Param("loginId") String loginId);

	public Member getMemberbyEmail(@Param("email") String email);

	@Update("""
			<script>
			UPDATE `member`
			<set>
				updateDate = NOW(),
				<if test="loginPw != null">
					loginPw = #{loginPw},
				</if>
				<if test="name != null">
					`name` = #{name},
				</if>
				<if test="nickname != null">
					nickname = #{nickname},
				</if>
				<if test="phoneNumber != null">
					phoneNumber = #{phoneNumber},
				</if>
				<if test="email != null">
					email = #{email}
				</if>
			</set>
			WHERE id = #{id}
			</script>
			""")
	public void modify(int id, String loginPw, String email, String name, String nickname, String phoneNumber);

}
