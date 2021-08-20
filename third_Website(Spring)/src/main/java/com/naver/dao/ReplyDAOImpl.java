package com.naver.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.vo.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	
	@Autowired //자동 의존성 주입
	private SqlSession sqlSession;//mybatis 쿼리문 수행 sqlSession을 생성

	@Override
	public void addReply(ReplyVO vo) {
		this.sqlSession.insert("reply_in",vo);
		//reply_in은 reply.xml에 설정할 유일한 insert 아이디명
	}//댓글등록

	@Override
	public List<ReplyVO> listReply(int bno) {
		return this.sqlSession.selectList("reply_list",bno);
		//mybatis에서 selectList()메소드는 하나이상의 레코드를 검색해서 컬렉션 List로 반환
		//reply_list는 reply.xml에서 설정할 유일한 select 아이디명이다.
	}//게시판 번호에 해당하는 댓글 목록

	@Override
	public void updateReply(ReplyVO vo) {
		this.sqlSession.update("reply_edit", vo);
	}//댓글번호를 기준으로 댓글을 수정

	@Override
	public void delReply(int rno) {
		this.sqlSession.delete("reply_del",rno);
		//reply_del은 reply.xml에서 설정할 유일한  delete 아이디 명이다.
		//mybatis에서 delete()메소드는 레코드를 삭제한다.
	}//댓글 삭제

	@Override
	public int getBno(int rno) {
		return this.sqlSession.selectOne("reply_bno",rno);
	}//댓글번호에 해당하는 게시판 번호 알아내기

}

