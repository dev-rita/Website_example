package com.naver.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.naver.dao.MemberDAO;
import com.naver.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/*.xml"})
public class MeberDAOTest {
	
	@Autowired//자동의존성 주입
	private MemberDAO memberDao;
	
	@Test
	public void testInsert() throws Exception{
		MemberVO m=new MemberVO();
		
		m.setUserid("ccccc");
		m.setUserpw("77777");
		m.setUsername("홍길동");
		m.setEmail("hong@naver.com");
		
		this.memberDao.insertMember(m);//회원저장. this.은 생략 가능함.
	}
}
