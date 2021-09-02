package net.daum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample/*")// 컨트롤러 자체에 sample 매핑 주소 등록
public class SampleController {
	@GetMapping("/all")//로그인 하지 않은 사용자도 접근 가능한 매핑 주소 > 모든 사용자 접근 가능.
	//get으로 접근하는 매핑주소를 처리하고 스프링 4.3에서 추가된 기능
	public void doAll() {
		//리턴 타입이 없으면 매핑주소가 뷰페이지 파일명이 된다. 뷰페이지 경로는 /WEB-INF/views/sample/all.jsp
		System.out.println("do all can access everybody!");
	}
	
	@GetMapping("/member") //member매핑주소를 등록, 로그인 한 사용자들만 접근가능 한 매핑주소
	public void doMember() {
		System.out.println("logined member");
	}
	
	@GetMapping("/admin")//로그인 한 사용자들 중에서 관리자 권한을 가진 사용자만 접근 가능함.
	public void doAdmin() {
		System.out.println("admin only");
	}
}
