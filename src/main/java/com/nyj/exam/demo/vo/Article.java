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
	
	public String getForPrintType2RegDate() {
		return regDate.substring(2, 19).replace(" " , "<br>");
	}; 
	public String getForPrintType2UpdateDate() {
		return updateDate.substring(2, 19).replace(" " , "<br>");
	};
	
	public String getForPrintType1RegDate() {
		return regDate.substring(2, 16);
	}; 
	public String getForPrintType1UpdateDate() {
		return updateDate.substring(2, 16);
	}; 
}
