package com.naver.dao;

import java.util.List;

import com.naver.vo.GongjiVO;

public interface GongjiDAO {

	List<GongjiVO> getGongjiList();
	void updateHit(int gongji_no);
	GongjiVO getGCont(int gongji_no);

}
