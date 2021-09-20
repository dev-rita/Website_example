package com.naver.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.vo.AdminVO;

@Repository
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertAdmin(AdminVO ab) {
		this.sqlSession.insert("admin_in",ab);//mybatis에서 insert()메서드는 레코드를 저장, admin_in은 admin.xml에서 설정할 유일한 insert 아이디명이다.		
	}//관리자 정보 저장	

	@Override
	public AdminVO adminLoginCheck(String admin_id) {
		return this.sqlSession.selectOne("admin_login",admin_id);//admin_login은 admin.xml에서 설정할 유일한 select 아이디명이다. mybatis에서
		//selectOne() 메서드는 단 한개의 레코드값만 반환
	}//관리자 로그인 인증
}
