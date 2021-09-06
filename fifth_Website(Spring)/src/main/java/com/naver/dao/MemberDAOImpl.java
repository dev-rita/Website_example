package com.naver.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.vo.MemberVO;
import com.naver.vo.ZipcodeVO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SqlSession sqlSession;//mybatis 쿼리문 수행 sqlSession

	@Override
	public MemberVO idCheck(String id) {
		return this.sqlSession.selectOne("m_check",id);
	}//아이디 중복 검색

	@Override
	public List<ZipcodeVO> zipFind(String dong) {
		return this.sqlSession.selectList("m_zip",dong);//mybatis에서 selectList()메서드는 하나이상의 레코드를 검색해서 컬렉션 List로 반환, m_zip은 
		//member.xml에서 설정할 유일한 select 아이디명이다.
	}//우편주소 검색

	@Override
	public void insertMember(MemberVO m) {
		this.sqlSession.insert("m_in",m);		
	}//회원저장

	@Override
	public MemberVO pwdMember(MemberVO m) {
		return this.sqlSession.selectOne("p_find",m);
	}//아이디와 회원이름을 기준으로 회원정보 검색

	@Override
	public void updatePwd(MemberVO m) {
		this.sqlSession.update("p_edit",m);		
	}//임시 비번 수정

	@Override
	public MemberVO loginCheck(String login_id) {
		return this.sqlSession.selectOne("login_ck",login_id);
	}//로그인 인증

	@Override
	public MemberVO getMember(String id) {
		return this.sqlSession.selectOne("m_edit",id);
	}//회원정보 가져오기

	@Override
	public void editMember(MemberVO m) {
		this.sqlSession.update("edit_ok",m);		
	}//정보 수정 완료

	@Override
	public void delMem(MemberVO dm) {
		this.sqlSession.update("m_del",dm);		
	}
}
