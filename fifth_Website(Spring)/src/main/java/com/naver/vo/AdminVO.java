package com.naver.vo;


import lombok.Data;

@Data
public class AdminVO {

	private int admin_no;
	private String admin_id;//관리자 아이디
	private String admin_pwd;//관리자 비번
	private String admin_name;//관리자 이름
	private String admin_date;//등록날짜
}
