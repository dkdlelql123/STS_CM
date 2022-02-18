package com.nyj.exam.demo.vo;

import lombok.Getter;

public class ResultData {

	@Getter
	private String resultCode;

	@Getter
	private String msg;
	
	@Getter
	private Object data;
	
	
	private ResultData() {
		
	}
	
	public static ResultData form(String resultCode, String msg) {
		return form(resultCode, msg, null);
	}
	
	public static ResultData form(String resultCode, String msg, Object data) {
		ResultData rd = new ResultData();
		rd.resultCode = resultCode;
		rd.msg = msg;
		rd.data = data;
		return rd;
	}
	
	public boolean isSuccess() {
		return resultCode.startsWith("s-");
	}
	
	public boolean isFail() {
		return isSuccess() == false;
	}
}
