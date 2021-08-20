package com.naver.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.naver.dao.BoardDAO;
import com.naver.vo.BoardVO;

@Service //@Service 를 함으로써 스프링에 서비스라는 것을 인식하게 한다.
public class BoardServiceImpl implements BoardService {
	
	@Inject//자동의존성 주입(DI)
	private BoardDAO boardDao;//mybatis 쿼리문 수행 sqlSession을 생성

	@Override
	public void insertBoard(BoardVO b) {
		this.boardDao.insertBoard(b);
	}

	@Override
	public int getTotalCount() {
		
		return this.boardDao.getTotalCount();
	}

	@Override
	public List<BoardVO> getBoardList(BoardVO b) {
		
		return this.boardDao.getBoardList(b);//this.은 생략 가능함
	}

	//스프링의 AOP를 통한 트랜잭션 적용대상
	@Transactional(isolation = Isolation.READ_COMMITTED)//트랜잭션 격리(트랜잭션이 처리되는 중간에 외부 간섭을 제거)
	@Override
	public BoardVO getBoardCont(int bno) {
		this.boardDao.updateHit(bno);//조회수 증가
		return this.boardDao.getCont(bno);//번호에 해당하는 레코드값을 가져옴.
	}

	@Override
	public BoardVO getBoardCont2(int bno) {
		return this.boardDao.getCont(bno);
	}//수정폼에서는 조회수를 증가하지 않는다.

	@Override
	public void editBoard(BoardVO eb) {
		this.boardDao.editBoard(eb);
	}

	@Override
	public void delBoard(int bno) {
		this.boardDao.delBoard(bno);
	}
}
