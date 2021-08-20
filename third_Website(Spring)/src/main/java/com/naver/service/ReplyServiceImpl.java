package com.naver.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naver.dao.BoardDAO;
import com.naver.dao.ReplyDAO;
import com.naver.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {
	@Inject
	private ReplyDAO replyDao;//자동 의존성 주입

	@Autowired
	private BoardDAO boardDao;
	
	//스프링의 AOP를 통한 트랜잭션 적용
	@Transactional
	@Override
	public void addReply(ReplyVO vo) {
		this.replyDao.addReply(vo);
		this.boardDao.updateReplyCnt(vo.getBno(),1);//댓글이 추가되면 댓글 수 1증가
	}

	@Override
	public List<ReplyVO> listReply(int bno) {
		return this.replyDao.listReply(bno);
	}

	@Override
	public void updateReply(ReplyVO vo) {
		this.replyDao.updateReply(vo);
	}
	
	//트랜잭션 적용
	@Transactional
	@Override
	public void remove(int rno) {
		int bno=this.replyDao.getBno(rno);//댓글번호로 게시판 번호값을 구함.
		this.replyDao.delReply(rno);
		this.boardDao.updateReplyCnt(bno,-1);//댓글을 삭제하면 댓글 개수 1감소
	}
}
