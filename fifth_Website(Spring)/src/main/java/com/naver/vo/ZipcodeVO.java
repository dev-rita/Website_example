package com.naver.vo;

import lombok.Data;

@Data
public class ZipcodeVO {

	private int no;
	private String zipcode; //우편번호
	private String sido;//시도
	private String gugun;//구군
	private String dong;//동
	private String bunji;//번지
}
