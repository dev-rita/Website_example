package com.naver.vo;

public class SampleVO {//데이터 저장 빈 클래스
	private int mno;//변수명이 JSON의 키이름이 된다.
	private String firstName;//이름
	private String lastName;//이름
	
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
