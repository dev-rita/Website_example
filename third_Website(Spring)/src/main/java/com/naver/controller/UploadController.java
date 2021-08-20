package com.naver.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	@GetMapping("uploadForm")//get방식으로 접근하는 매핑 주소를 처리. @GetMapping은 스프링 4.3에서 추가됨.
	public void uploadForm() {
		//리턴타입이 없으면 매핑주소가 jsp 파일명이 된다.
	}
	
	@PostMapping("/uploadFormAction")//post로 접근하는 매핑주소를 처리, @PostMapping은 스프링 4.3에서 추가됨.
	public void uploadFormAction(MultipartFile[] uploadFile) {
		//스프링의 MultipartFile 타입을 이용해서 첨부파일 데이터를 쉽게 받아옴.
		//다중 업로드 파일은 배열로 받는다. input type="file"의 네임 파라미터 이름인 uploadFile을
		//메소드 매개변수명으로 지정해서 처리한다.
		
		String uploadFolder = "C:\\upload";//이진파일 업로드 서버 경로
		
		//자바 5에서 추가된 향상된 확장 for반복문으로 처리
		for(MultipartFile multipartFile : uploadFile) {
			System.out.println("-------------------------");
			System.out.println("첨부된 원본 파일명:"+multipartFile.getOriginalFilename());
			System.out.println("업로드 파일 크기:"+multipartFile.getSize());
			
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			
			try {
				multipartFile.transferTo(saveFile);//첨부된 원본 파일명으로 upload폴더에 실제 업로드 함.
			}catch(Exception e) {
				e.printStackTrace();
			}
		}//for
	}//uploadFormAction()
	
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		//리턴 타입이 없으면 매핑주소가 뷰페이지 파일명이 된다. > 뷰페이지 경로(뷰리졸브 경로)는 /WEB-INF/views/uploadAjax.jsp
		//jsp가 직접 웹브라우저에 실행이 안되고 WEB-INF를 거쳐 실행됨
	}//ajax를 이용한 파일 업로드
	
	@PostMapping("/uploadAjaxAction")
	public void uploadAjaxAction(MultipartFile[] uploadFile) {
		
		System.out.println("upload ajax post.....");
		
		String uploadFolder="C:\\upload"; //이진파일 업로드 서버 경로
		
		for(MultipartFile multipartFile:uploadFile) {
			System.out.println("===============================>");
			System.out.println("첨부한 원본 파일명 :"+multipartFile.getOriginalFilename());
			System.out.println("첨부한 파일 크기 :"+multipartFile.getSize());
			
			String uploadFileName=multipartFile.getOriginalFilename();
			uploadFileName=uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
			//lastIndexOf()a메소드에 의해서 \\의 문자를 맨 오른쪽 부터 찾아서 가장 먼저 나오는 위치번호를 맨 왼쪽 첫문자를
			//0부터 시작해 카운터 한 다음  해당 위치번호를 반환한다. 해당 위치번호 이후부터 마지막 문자 즉 첨부한 파일명을 구함.
			//>>IE인 경우 전체 파일경로가 전송되기 때문에 첨부한 파일명만 구하기 위해서이다.
					
			System.out.println("only file name:"+uploadFileName);
			File saveFile=new File(uploadFolder,uploadFileName);
			
			try {
				multipartFile.transferTo(saveFile);//실제 업로드
			}catch(Exception e) {
				e.printStackTrace();
			}
			}//향상된 확장 for
	}//uploadAjaxAction()
}
