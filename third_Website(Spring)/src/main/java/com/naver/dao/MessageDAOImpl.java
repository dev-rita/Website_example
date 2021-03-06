package com.naver.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.vo.MessageVO;

@Repository
public class MessageDAOImpl implements MessageDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void create(MessageVO vo) {
		this.sqlSession.insert("m_in2",vo);
	}
}
