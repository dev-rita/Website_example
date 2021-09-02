package net.daum.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.MemberVO;

@Repository
public class MemberMapperDAOImpl implements MemberMapper {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public MemberVO read(String userid) {
		return this.sqlSession.selectOne("read",userid);
	}
}
