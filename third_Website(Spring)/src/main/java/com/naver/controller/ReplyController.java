package com.naver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.naver.service.ReplyService;
import com.naver.vo.ReplyVO;

@RestController
@RequestMapping("/replies")//컨트롤러 자체에 매핑주소 등록
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	//댓글등록
	//@PostMapping //post로 접근하는 매핑주소를 처리
	@RequestMapping(value="",method=RequestMethod.POST)//post로 접근하는 매핑주소를 처리
	public ResponseEntity<String> register(@RequestBody ReplyVO vo){
		//@RequestBody ReplyVO vo는 전송된 JSON 데이터를 ReplyVO 객체 타입으로 변환해준다.
		//데이터 전송방식은 JSON을 이용한다.
		ResponseEntity<String> entity=null;
		
		try {
			this.replyService.addReply(vo);//댓글 추가
			entity=new ResponseEntity<>("SUCCESS",HttpStatus.OK);
			//댓글 저장 성공시 SUCCESS문자가 반환되고 200정상상태 코드가 반환
		}catch(Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
			//예외 에러가 발생하면 예외 에러 메시지와 나쁜 상태 코드가 반환
		}
		return entity;
	}//register()
	
	//게시물 번호에 해당하는 댓글 목록
	@RequestMapping(value="all/{bno}",produces="application/json")
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno") int bno){
		//@PathVariable("bno")는 매핑주소의 게시물 번호값을 추출하는 용도
		
		ResponseEntity<List<ReplyVO>> entity=null;
		
		try {
			entity=new ResponseEntity<>(this.replyService.listReply(bno),HttpStatus.OK);
			//게시물 번호에 해당하는 댓글 목록을 가져옴.
		}catch(Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}//list()
	
	//댓글 수정
	@RequestMapping(value="/{rno}", method= {RequestMethod.PUT, RequestMethod.PATCH})
	//PUT은 전체자료를 수정, PATCH는 일부 자료만 수정
	public ResponseEntity<String> update(@PathVariable("rno") int rno, @RequestBody ReplyVO vo){
		ResponseEntity<String> entity=null;
		try {
			vo.setRno(rno);//댓글 번호를 저장
			replyService.updateReply(vo);//댓글 수정, this.이 생략됨
			entity=new ResponseEntity<>("SUCCESS",HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			entity=new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}//update()
	
	//댓글 삭제
	@RequestMapping(value="{rno}", method=RequestMethod.DELETE)// "/"생략 가능
	public ResponseEntity<String> remove(@PathVariable("rno") int rno){
		ResponseEntity<String> entity=null;
		
		try {
			this.replyService.remove(rno);//댓글삭제
			entity=new ResponseEntity<>("SUCCESS",HttpStatus.OK);
		}catch(Exception e) {
			entity=new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}//remove()
	
}
