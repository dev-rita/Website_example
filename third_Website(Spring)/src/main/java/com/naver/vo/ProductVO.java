package com.naver.vo;

public class ProductVO {//자료 저장 빈 클래스
	private String name;//상품명
	private double price;//상품 가격
	
	public ProductVO(String name, double price) {
		this.name=name;
		this.price=price;
	}//생성자 오버로딩

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}
	
	
}
