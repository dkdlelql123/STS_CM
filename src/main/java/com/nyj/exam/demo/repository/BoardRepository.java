package com.nyj.exam.demo.repository;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.nyj.exam.demo.vo.Board;

@Mapper
public interface BoardRepository {

	@Select("""
	Select * from board where delStatus = 0		
	""")
	ArrayList<Board> findAll();

	@Select("""
			SELECT * FROM board WHERE id = #{id} AND delStatus = 0
    """)
	Board findById(@Param("id") int id);


}
