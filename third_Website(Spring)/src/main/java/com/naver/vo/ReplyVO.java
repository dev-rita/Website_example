package com.naver.vo;

import lombok.Getter;
import lombok.Setter;

@Setter//setter()메소드 자동제공
@Getter//getter()메소드 자동제공
public class ReplyVO {//tbl_reply 테이블의 컬럼명과 빈클래스 변수명을 되도록이면 같게 한다.(코드라인을 줄일 수 있다.)
	private int rno;//댓글 번호
	private int bno;//게시판 번호
	private String replyer;//댓글 작성자
	private String replytext;//댓글 내용
	private String regdate;//등록날짜
	private String updatedate;//수정날짜
}
