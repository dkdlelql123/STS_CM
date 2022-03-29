package com.nyj.exam.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyj.exam.demo.repository.BoardRepository;
import com.nyj.exam.demo.vo.Board;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepositofy;

	public Board findById(int boardId) {
		return boardRepositofy.findById(boardId) ;
	}
}
