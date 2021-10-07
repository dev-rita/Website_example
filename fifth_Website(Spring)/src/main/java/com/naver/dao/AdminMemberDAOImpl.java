package com.naver.dao;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.naver.vo.MemberVO;

@Repository
public class AdminMemberDAOImpl implements AdminMemberDAO {

	@Inject //자동의존성 주입
	private SqlSessionTemplate sqlSession; //sqlSession은 mybatis 쿼리문 수행 

	@Override
	public int getListCount(MemberVO m) {
		return this.sqlSession.selectOne("am_count",m);//mybatis에서는 단 한개의 레코드를 반환할 때는 selectOne()메서드를 사용한다.
	}//검색전,후 회원 수

	@Override
	public List<MemberVO> getMemberList(MemberVO m) {
		return this.sqlSession.selectList("am_list",m);//selectList()메서드는 복수개의 레코드를 검색해서 컬렉션 List로 반환한다. am_list는 mybatis 매퍼태
		//그에 설정할 유일한 select 아이디명이다.
	}//검색 전,후 회원 목록

	@Override
	public MemberVO getMem(String mem_id) {
		return this.sqlSession.selectOne("am_info",mem_id);
	}//회원상세정보와 수정폼

	@Override
	public void editM(MemberVO m) {
		this.sqlSession.update("am_edit", m);//update()메서드로 레코드 수정, am_edit는 매퍼태그에서 설정할 유일한 update 아이디명이다.		
	}//관리자에서 회원정보 수정

	@Override
	public void delMember(String mem_id) {
		this.sqlSession.delete("am_del",mem_id);		
	}//관리자에서 회원 삭제
}
