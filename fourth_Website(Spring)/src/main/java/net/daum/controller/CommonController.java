package net.daum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommonController {
	
	@GetMapping("/accessError")//get으로 접근하는 매핑주소를 처리, accessError 매핑주소 등록
	public void accessDenied(Model model){
		//반환타입이 없는 void형이면 매핑주소가 뷰페이지 명이 된다. >> 뷰리졸브 경로 (/WEB-INF/vies/accessError.jsp)
		
		System.out.println("access Denied");
		model.addAttribute("msg","Access Denied");
	}
	
	@GetMapping("/customLogin")
	public void loginInput(String error, String logout, Model model) {
		System.out.println("error:"+error);
		System.out.println("logout:"+logout);
		
		if(error !=null) {
			model.addAttribute("error",error);
		}
		
		if(logout !=null) {
			model.addAttribute("logout","Logout!!");
		}
	}//customLogin 매핑주소 등록
	
	@GetMapping("/customLogout")//get으로 접근하는 매핑주소 처리
	public void logoutGET() {
		
	}

	@PostMapping("/customLogout") //post로 접근하는 매핑주소 처리
	public void logoutPost() {
		
	}
}
