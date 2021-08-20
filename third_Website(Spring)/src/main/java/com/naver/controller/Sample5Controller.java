package com.naver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.naver.vo.ProductVO;

@Controller
public class Sample5Controller {
	@RequestMapping(value="/doJSON",produces="application/json")
	//doJSON매핑주소 등록
	
	public @ResponseBody ProductVO doJSON() {
		//@ResponseBody 애노테이션 jsp 파일을 만들지 않고도 브라우저에 키, 값 쌍의 JSON데이터를 쉽게 만들 수 있다.
		//JSON의 키이름은 ProductVO.java의 변수명이 된다.
		ProductVO p=new ProductVO("복숭아",5000);
		return p;
	}
}
