package com.nyj.exam.demo.vo;

import lombok.Getter;
import lombok.ToString;

@ToString
public class ResultData {

	@Getter
	private String resultCode;

	@Getter
	private String msg;
	
	@Getter
	private String data1Name;
	
	@Getter
	private Object data1;
	
	
	private ResultData() {
		
	}
	
	public static ResultData form(String resultCode, String msg) {
		return form(resultCode, msg, null, null);
	}
	
	public static ResultData form(String resultCode, String msg, String data1Name, Object data1) {
		ResultData rd = new ResultData();
		rd.resultCode = resultCode;
		rd.msg = msg;
		rd.data1Name = data1Name;
		rd.data1 = data1;
		return rd;
	}
	
	public boolean isSuccess() {
		return resultCode.startsWith("s-");
	}
	
	public boolean isFail() {
		return isSuccess() == false;
	}

	public static ResultData newData(ResultData oldRd, String data1Name, Object data1) {
		return form(oldRd.resultCode, oldRd.msg, data1Name, data1);
	}
}
