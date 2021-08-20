package com.naver.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naver.vo.SampleVO;

@RestController //스프링 4.0 이후부터는 이 애노테이션으로 jsp등 뷰페이지를 만들지 않고도 
//Rest방식의 원하는 데이터 즉 키, 값 쌍의 json, xml, 문자열 객체등을 만들 수 있다.
@RequestMapping("/sample")//컨트롤러 자체에 매핑주소 등록
public class Sample6Controller {
	@RequestMapping("/hello")//hello 매핑주소 등록, get or post로 접근하는 매핑주소를 처리
	public String hello() {
		return "Rest API Begin";
	}

	@RequestMapping(value="/sendVO", produces="application/json")
	public SampleVO sendVO() {
		//리턴타입이 SampleVO이면 변수명이 JSON객체의 키이름이 된다.
		SampleVO vo=new SampleVO();
		vo.setFirstName("순신");//FirstName이 키, 순신이 값
		vo.setLastName("이");
		vo.setMno(10);
		return vo;
	}
	
	@RequestMapping(value="/sendList", produces="application/json")
	public List<SampleVO> sendList(){
		List<SampleVO> list=new ArrayList<>();
		for(int i=1;i<=5;i++) {
			SampleVO vo=new SampleVO();
			vo.setMno(i);
			vo.setFirstName("사임당");
			vo.setLastName("신");
			
			list.add(vo);
		}
		return list;
	}//sendList()
	
	//키, 값 쌍의 Map 타입 JSON
	@RequestMapping(value="/sendMap",produces="application/json")
	public Map<Integer,SampleVO> sendMap(){
		Map<Integer,SampleVO> map=new HashMap<>();
		
		for(int i=1;i<=5;i++) {
			SampleVO vo=new SampleVO();
			
			vo.setMno(i);
			vo.setFirstName("길동");
			vo.setLastName("홍");
			
			map.put(i, vo);//컬렉션에 키, 값 저장
		}
		return map;
	}//sendMap()
	@RequestMapping("/sendError")
	public ResponseEntity<Void> sendListAuth(){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		/* @RestController는 별도의 jsp파일을 만들지 않고도 Rest api 서비스를 실행하기 때문에
		 * 결과 데이터에 예외적인 상황에 문제가 발생할 수 있다. 그러므로 스프링에서 제공하는
		 * ResponseEntity api로 나쁜상태 코드에 대한 좀 더 세밀한 에러를 다룰 수 있다.
		 * 여기서는 400나쁜상태코드(BAD_REQUEST)가 브라우저로 전송된다.
		 * */
	}
	
	//정상적인 json 데이터와 404(해당 자원파일을 찾지 못했을 때 발생) 나쁜 상태코드가 함께 브라우저로 전송
	@RequestMapping(value="/sendErrorNot", produces = "application/json")
	public ResponseEntity<List<SampleVO>> sendListNot(){
		List<SampleVO> list=new ArrayList<>();
		for(int i=1;i<=3;i++) {
			SampleVO vo=new SampleVO();
			
			vo.setMno(i);
			vo.setFirstName("문덕");
			vo.setLastName("을지");
			
			list.add(vo);//컬렉션에 추가
		}//for
		return new ResponseEntity<List<SampleVO>>(list,HttpStatus.NOT_FOUND);//NOT_FOUND가 404 나쁜상태코드
	}
}

