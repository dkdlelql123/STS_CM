package com.nyj.exam.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
	private int id;
	private String title;
	private String body;
	
	private String regDate;
	private String updateDate;
	
	private int boardId;
	private int memberId;
	
	private int hit;
	
	private String extra_actorName;
	private boolean extra_actorCanModify;
	private boolean extra_actorCanDelete;
}
