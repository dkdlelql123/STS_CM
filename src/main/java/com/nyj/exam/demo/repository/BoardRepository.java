package com.nyj.exam.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.nyj.exam.demo.vo.Board;

@Mapper
public interface BoardRepository {

	@Select("""
			SELECT * FROM board WHERE id = #{id} AND delStatus = 0
""")
	Board findById(@Param("id") int id);

}
