package com.naver.vo;

import lombok.Data;

@Data //setter(),getter(),toString(),기본생성자 자동 제공
public class MessageVO {
	//tbl_message 테이블의 컬럼명과 빈 클래스 변수명을 되도록이면 같게 한다.(필수는 아님)
	//매퍼태그의 코드라인을 줄일 수 있다.
	
	private int mid;
	private String targetid;
	private String sender;
	private String message;
	private String senddate;
	
}
