package com.naver.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PointDAOImpl implements PointDAO {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void updatePoint(String sender, int point) {//복수개의 파라미터를 넘기려면 Map을 사용하는 것이 좋다.
		Map<String,Object> pm=new HashMap<>();
		pm.put("sender", sender);//왼쪽에 있는 키이름을 point.xml 매퍼태그에서 참조해서 보낸사람을 가져옴.
		pm.put("point",point);//포인트 점수
		
		this.sqlSession.update("pointUp",pm);//pointUp은 point.xml에서 설정할 유일한 update 아이디명이다.
	}
}
