package com.naver.vo;

import lombok.Data;

@Data
public class UserVO {
	//tbl_user테이블의 컬럼명과 빈클래스 변수명을 되도록이면 같게 한다.
	private String uid2;
	private String upw;
	private String uname;
	private int Upoint;
	
}
