package com.naver.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.vo.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Autowired //자동 의존성 주입
	private SqlSession sqlSession;//mybatis 쿼리문 수행 sqlSession을 생성

	@Override
	public void insertBoard(BoardVO b) {
		this.sqlSession.insert("b_in",b);//mybatis에서 insert()메소드는 레코드를 저장.
		//b_in은 board.xml에서 설정할 유일한 insert 아이디명
	}//게시판 저장

	@Override
	public int getTotalCount() {
		return this.sqlSession.selectOne("b_count");
		//mybatis에서 selectOne()메소드는 단 한개의 레코드값만 반환. 
		//b_count는 board.xml에서 설정할 유일한 select 아이디명이다.
	}//총 레코드 개수
	
	@Override
	public List<BoardVO> getBoardList(BoardVO b) {
		return this.sqlSession.selectList("b_list",b);
		//mybatis에서 selectList()메소드는 하나 이상의 레코드를 검색해서 컬렉션 List로 반환.
		//b_list는 board.xml에서 설정할 유일한 select 아이디명이다.
	}//게시판 목록

	@Override
	public void updateHit(int bno) {
		this.sqlSession.update("b_hit",bno);//mybatis에서 update() 메소드는 레코드를 수정.
		//b_hit는 board.xml에서 설정할 유일한 update 아이디명
		
	}//조회수 증가
	
	
	@Override
	public BoardVO getCont(int bno) {
		return this.sqlSession.selectOne("b_cont",bno);
	}//내용보기

	@Override
	public void editBoard(BoardVO eb) {
		this.sqlSession.update("b_edit",eb);
	}//게시판 수정

	@Override
	public void delBoard(int bno) {
		this.sqlSession.delete("b_del",bno);//mybatis에서 delete()메소드는 레코드를 삭제.
		//b_del은 board.xml에서 설정할 유일한 delete id명이다.
	}//게시판 삭제

	@Override
	public void updateReplyCnt(int bno, int count) {
		Map<String,Object> pm=new HashMap<>();//키, 값 쌍으로 저장하는 컬렉션 제네릭 선언
		
		pm.put("bno", bno);//좌측의 키이름을 매퍼태그에서 참조해서 값을 가져옴.
		pm.put("count",count);
		
		this.sqlSession.update("updateReplyCnt",pm);//updateReplyCnt는  board.xml에 설정할 유일한 update 아이디명.
	}//댓글이 추가되면 댓글 수 1증가, 댓글이 삭제되면 댓글 수 1감소
}
