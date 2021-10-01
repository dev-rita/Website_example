package com.naver.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.vo.BbsVO;

@Repository
public class AdminBbsDAOImpl implements AdminBbsDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int getTotalCount(BbsVO b) {
		return this.sqlSession.selectOne("abbs_row",b);
	}//관리자 자료실 검색 전후 레코드  개수

	@Override
	public List<BbsVO> getBbsList(BbsVO b) {
     	return this.sqlSession.selectList("abbs_list",b);
	}//검색전후 관리자 자료실 목록

	@Override
	public void insertBbs(BbsVO b) {
		this.sqlSession.insert("abbs_in",b);		
	}//관리자 자료실 저장

	@Override
	public BbsVO getBbsCont(int no) {
		return this.sqlSession.selectOne("abbs_cont",no);
	}//관리자 자료실 상세정보 보기와 수정폼

	@Override
	public void editBbs(BbsVO b) {
		this.sqlSession.update("abbs_edit",b);		
	}//관리자 자료실 수정

	@Override
	public void delBbs(int no) {
		this.sqlSession.delete("abbs_del",no);		
	}//관리자 자료실 삭제
}
