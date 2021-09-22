package com.naver.service;

import java.util.List;

import com.naver.vo.GongjiVO;

public interface AdminGongjiService {

	int getListCount(GongjiVO g);
	List<GongjiVO> getGongjiList(GongjiVO g);
	void insertG(GongjiVO g);
	GongjiVO getGongjiCont(int no);
	void updateGongji(GongjiVO g);
	void deleteGongji(int no);

}
