package com.naver.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.naver.vo.ReplyVO;

public interface ReplyService {

	void addReply(ReplyVO vo);

	 List<ReplyVO> listReply(int bno);

	 void updateReply(ReplyVO vo);

	void remove(int rno);
	

}
