package com.nyj.exam.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	private int id;
	private String loginId;
	private String loginPw;
	private int authLevel;
	private String email;
	private String name;
	private String nickname;
	private String phoneNumber;
	private boolean delStatus;
	private String delDate; 
}
