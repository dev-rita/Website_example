package com.naver.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.naver.vo.MemberVO;

@Repository //@Repository 애노테이션으로 DAOImpl을 스프링에서 인식하게 한다.
public class MemberDAOImpl implements MemberDAO {

	@Inject //자동의존성 주입(DI:Dependency Injection)
	private SqlSession sqlSession; //mybatis 쿼리문 수행 sqlSession을 생성

	@Override
	public void insertMember(MemberVO m) {
		this.sqlSession.insert("m_in",m);//mybatis에서 insert()메소드는 레코드를 저장시킨다.
		//m_in은member.xml매퍼 태그에서 설정할 유일한 insert 아이디명이다.
		
	}//회원저장
}
