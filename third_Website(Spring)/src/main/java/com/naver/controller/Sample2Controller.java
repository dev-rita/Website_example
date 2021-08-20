package com.naver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class Sample2Controller {
	
	@GetMapping("doC") //get으로 접근하는 doC매핑주소를 처리
	public String doC(@ModelAttribute("cityName") String name) {
		//@ModelAttribute("cityName")은 cityName파라미터 이름에 값을 담아서 전달.
		//doC?cityname=인자값 형태의 주소창에 노출되는 get방식으로 전달한다.
		//보안성은 좋지 않다.
		return "result";//뷰리졸브 즉 뷰페이지 경로는 /WEB-INF/views/result.jsp가 된다.
	}
}
