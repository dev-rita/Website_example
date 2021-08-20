package com.naver.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.vo.BoardNightVO;

@Repository
public class BoardNightDAOImpl implements BoardNightDAO{
	
	@Autowired 
	private SqlSession sqlSession;

	@Override
	public void insertBoard(BoardNightVO nb) {
		this.sqlSession.insert("nb_in",nb);
	}

	@Override
	public int getTotalCount() {
		return this.sqlSession.selectOne("nb_count");
	}
}
