package com.naver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller//@Controller 애너테이션을 사용하면 해당 컨트롤러 클래스는 스프링에서 인식한다.
public class SampleController {

	@RequestMapping("doA")//GET OR POST 방식으로 접근하는 doA매핑주소를 처리
	
	public void doA() {
		//반환 타입이 없으면 매핑주소인 doA가 뷰페이지 파일명이 된다.
		//(doA.jsp) > 뷰리졸브 경로에 해당 파일이 없으면 404 에러가 발생.
		System.out.println("doA 매핑 주소가 실행됨");//이클립스 콘솔모드에 출력
	}
	
	@RequestMapping("doB")
	
	public void doB() {
		System.out.println("doB 매핑 주소가 실행됨");
	}
}
