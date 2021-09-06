package com.naver.vo;

import lombok.Getter;
import lombok.Setter;

@Setter //setter() 메서드 자동 제공
@Getter //getter() 메서드 자동 제공
public class MemberVO {//데이터 저장빈 클래스
	
	private String mem_id;//회원 아이디
	private String mem_pwd;//비번
	private String mem_name;//회원이름
	private String mem_zip;//우편번호
	private String mem_zip2;
	private String mem_addr; //주소
	private String mem_addr2;//나머지 주소
	private String mem_phone01;//폰번호
	private String mem_phone02;
	private String mem_phone03;
	private String mail_id;//메일 아이디
	private String mail_domain;//메일 도메인
    private String mem_date;
    private int mem_state;//가입회원이면 1, 탈퇴 회원이면 2
    private String mem_delcont; //탈퇴사유
    private String mem_deldate; //탈퇴 날짜
    
    //관리자 회원목록 페이징 관련 변수
    private int startrow;//시작행 번호
    private int endrow; //끝행 번호
    
    //관리자 회원목록 검색필드와 검색어 관련 변수
    private String find_name;//검색어
    private String find_field;//검색필드    
}





