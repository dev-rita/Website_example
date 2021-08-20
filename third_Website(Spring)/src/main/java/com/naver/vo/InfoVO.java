package com.naver.vo;

public class InfoVO {
	private String firstName;//성
	private String lastName;//이름

	public InfoVO(String firstName,String lastName) {
		this.firstName=firstName;
		this.lastName=lastName;//멤버변수 초기화
	}//매개변수 개수를 다르게 한 생성자 오버로딩

	public String getfirstName() {
		return firstName;
	}

	public String getlastName() {
   		return lastName;   
	}//getter() 메서드 정의	
}
