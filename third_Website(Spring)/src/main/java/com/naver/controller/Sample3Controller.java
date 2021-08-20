package com.naver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naver.vo.ProductVO;

@Controller
public class Sample3Controller {
	
	@RequestMapping("/notePrice")//notePrice 매핑주소 등록
	public String notePrice(Model m) {
		ProductVO p=new ProductVO("노트북",1500000);
		
		m.addAttribute("p",p);//p속성 키 이름에 p 객체 저장
		return "shop/note";//뷰페이지 경로가 /WEB-INF/views/shop/note.jsp
	}
}
