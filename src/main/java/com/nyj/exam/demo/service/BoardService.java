package com.nyj.exam.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyj.exam.demo.repository.BoardRepository;
import com.nyj.exam.demo.vo.Board;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepositofy;

	public ArrayList<Board> findAll() {
		return boardRepositofy.findAll();
	}
	
	public Board findById(int boardId) {
		return boardRepositofy.findById(boardId) ;
	}

}
