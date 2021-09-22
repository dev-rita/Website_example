package com.naver.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.vo.GongjiVO;

@Repository
public class AdminGongjiDAOImpl implements AdminGongjiDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int getListCount(GongjiVO g) {
		return this.sqlSession.selectOne("ag_count",g);
	}//검색전후 레코드 개수

	@Override
	public List<GongjiVO> getGongjiList(GongjiVO g) {
		return this.sqlSession.selectList("ag_list",g);
	}//검색 전후 공지목록

	@Override
	public void insertG(GongjiVO g) {
		this.sqlSession.insert("ag_in",g);		
	}//관리자 공지 저장

	@Override
	public GongjiVO getGongjiCont(int no) {
		return this.sqlSession.selectOne("ag_cont",no);
	}//관리자 공지 상세 내용보기

	@Override
	public void updateGongji(GongjiVO g) {
		this.sqlSession.update("ag_edit",g);		
	}//관리자 공지 수정

	@Override
	public void deleteGongji(int no) {
		this.sqlSession.delete("ag_del",no);		
	}//관리자 공지 삭제
}
