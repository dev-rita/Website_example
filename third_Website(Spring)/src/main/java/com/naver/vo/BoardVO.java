package com.naver.vo;

public class BoardVO {//데이터 저장 빈 클래스 -> 되도록이면 테이블 컬럼명과 빈클래스 변수명을 같게 한다.
	private int bno;
	private String writer;
	private String title;
	private String content;
	private int viewcnt;//조회수
	private String regdate;//등록날짜
	private int replycnt;//댓글 수 카운터
	
	//페이징(쪽나누기)
	private int startrow;//시작행 번호
	private int endrow;//끝행 번호
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getViewcnt() {
		return viewcnt;
	}
	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getStartrow() {
		return startrow;
	}
	public void setStartrow(int startrow) {
		this.startrow = startrow;
	}
	public int getEndrow() {
		return endrow;
	}
	public void setEndrow(int endrow) {
		this.endrow = endrow;
	}
	public int getReplycnt() {
		return replycnt;
	}
	public void setReplycnt(int replycnt) {
		this.replycnt = replycnt;
	}
		
}
