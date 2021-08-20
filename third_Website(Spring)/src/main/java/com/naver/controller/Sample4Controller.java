package com.naver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class Sample4Controller {
	
	@RequestMapping("doE")
	public String doE(RedirectAttributes rttr) {
		rttr.addFlashAttribute("msg","홍길동");
		//msg속성 키이름에 홍길동을 담아서 전달한다. 서버상에서 실행되기 때문에 주소창에 노출안된다.
		//보안상 좋다.
		return "redirect:/doF";//doE매핑주소가 실행되면 다른 매핑주소 doF로 이동한다.
	}
	
	@GetMapping("/doF")
	public void doF(@ModelAttribute("msg") String name) {
		System.out.println("전달된 이름:"+name);
	}
}
