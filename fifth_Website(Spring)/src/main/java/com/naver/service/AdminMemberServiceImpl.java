package com.naver.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.naver.dao.AdminMemberDAO;
import com.naver.vo.MemberVO;

@Service
public class AdminMemberServiceImpl implements AdminMemberService {

	@Inject
	private AdminMemberDAO adminMemberDao;

	@Override
	public int getListCount(MemberVO m) {
		return this.adminMemberDao.getListCount(m);
	}

	@Override
	public List<MemberVO> getMemberList(MemberVO m) {
		return this.adminMemberDao.getMemberList(m);
	}

	@Override
	public MemberVO getMem(String mem_id) {
		return this.adminMemberDao.getMem(mem_id);
	}

	@Override
	public void editM(MemberVO m) {
		this.adminMemberDao.editM(m);		
	}

	@Override
	public void delMember(String mem_id) {
		this.adminMemberDao.delMember(mem_id);		
	}
}
