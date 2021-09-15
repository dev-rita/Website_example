package com.naver.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.vo.GongjiVO;

@Repository
public class GongjiDAOImpl implements GongjiDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<GongjiVO> getGongjiList() {
		return this.sqlSession.selectList("g_list");
	}//최신 공지목록 5개 가져오기

	@Override
	public void updateHit(int gongji_no) {
	    this.sqlSession.update("g_hit",gongji_no);			
	}//공지 조회수 증가

	@Override
	public GongjiVO getGCont(int gongji_no) {
		return this.sqlSession.selectOne("g_cont",gongji_no);
	}//공지 내용보기
}
