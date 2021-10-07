package com.naver.service;

import java.util.List;

import com.naver.vo.MemberVO;

public interface AdminMemberService {

	int getListCount(MemberVO m);
	List<MemberVO> getMemberList(MemberVO m);
	MemberVO getMem(String mem_id);
	void editM(MemberVO m);
	void delMember(String mem_id);

}
