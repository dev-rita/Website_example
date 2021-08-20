package com.naver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naver.vo.InfoVO;

@RestController //스프링 4.0 이후부터는 이 애노테이션으로 jsp등 뷰페이지를 만들지 않고도 
//Rest방식의 원하는 데이터 즉 키, 값 쌍의 json, xml, 문자열 객체등을 만들 수 있다.
public class InfoController {
	
		@RequestMapping(value="/p_name.json", produces="application/json")
		public InfoVO sendVO() {
			InfoVO vo=new InfoVO("홍","길동");
			return vo;
		}
}
