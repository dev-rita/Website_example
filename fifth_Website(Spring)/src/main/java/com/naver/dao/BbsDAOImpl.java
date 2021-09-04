package com.naver.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.vo.BbsVO;

@Repository
public class BbsDAOImpl implements BbsDAO {

	@Autowired //자동 의존성 주입
	private SqlSession sqlSession; //sqlSession은 mybatis에서 쿼리문 수행

	@Override
	public void insertBbs(BbsVO b) {
		this.sqlSession.insert("bbs_in",b);//mybatis에서 insert()메서드는  레코드를 저장시킴. bbs_in은 bbs.xml에 설정할 유일한 insert 아이디명이다.
	}//자료실 저장

	@Override
	public int getTotalCount(BbsVO b) {
		return this.sqlSession.selectOne("bbs_count",b);
		//mybatis에서 selectOne()메서드는 단 한개의 레코드값만 반환, bbs_count는 bbs.xml에서 설정할 유일한 select 아이디명.
	}//검색전후 레코드 개수

	@Override
	public List<BbsVO> getBbsList(BbsVO b) {
		return this.sqlSession.selectList("bbs_list",b);//mybatis에서 selectList() 메서드는 하나이상의 레코드를 검색해서 컬렉션 List로 반환
		//bbs_list는 bbs.xml에서 설정할 유일한 select 아이디명
	}//검색전,후 페이징 목록

	@Override
	public void updateHit(int bbs_no) {
		this.sqlSession.update("bbs_hi",bbs_no);//mybatis에서 update()메서드는 레코드를 수정, bbs_hi는 update 유일 아이디명		
	}//조회수 증가

	@Override
	public BbsVO getBbsCont(int bbs_no) {
		return sqlSession.selectOne("bbs_co", bbs_no);//this.은 생략가능함.
	}//내용보기+답변폼+수정폼+삭제폼

	@Override
	public void updateLevel(BbsVO rb) {
		this.sqlSession.update("level_up",rb);
	}//답변 레벨 증가

	@Override
	public void replyBbs(BbsVO rb) {
	   this.sqlSession.insert("reply_in2",rb);		
	}//답변 저장

	@Override
	public void editBbs(BbsVO b) {
		this.sqlSession.update("bbs_edit", b);		
	}//자료실 수정

	@Override
	public void delBbs(int bbs_no) {
		sqlSession.delete("bbs_del",bbs_no);//mybatis에서 bbs_del은 유일한 delete 아이디명이다. delete()메서드는 레코드를 삭제		
	}
}
