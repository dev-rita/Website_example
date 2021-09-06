package com.naver.vo;

import lombok.Data;

@Data //setter(), getter() 메서드 자동 제공
public class ZipcodeVO2 {

	private String zipcode;//우편번호
	private String addr;//시도 구군 동
}
