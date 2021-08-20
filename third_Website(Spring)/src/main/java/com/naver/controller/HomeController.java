package com.naver.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller //@Controller 애노테이션으로 스프링 컨트롤러 클래스를 만든다.
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)//get으로 접근하는 매핑주소를 처리
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );//serverTime 속성키이름에 날짜 포맷 시간 저장
		
		return "home";//뷰페이지 경로 -> /WEB-INF/views/home.jsp
	}
	
	//jQuery+ajax로 댓글 뷰페이지 처리
	@RequestMapping("/test")//test매핑주소 등록, get or post 방식으로 접근하는 매핑주소를 처리
	public void test() {
		//리턴(반환)타입이 없으면 매핑주소가 jsp 즉 뷰페이지 파일명이 된다. 
		//뷰페이지 경로(뷰 리졸브 경로)->/WEB-INF/views/test.jsp
	}
	
}
