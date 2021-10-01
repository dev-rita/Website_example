package com.naver.dao;

import java.util.List;

import com.naver.vo.BbsVO;

public interface AdminBbsDAO {

	int getTotalCount(BbsVO b);
	List<BbsVO> getBbsList(BbsVO b);
	void insertBbs(BbsVO b);
	BbsVO getBbsCont(int no);
	void editBbs(BbsVO b);
	void delBbs(int no);

}
