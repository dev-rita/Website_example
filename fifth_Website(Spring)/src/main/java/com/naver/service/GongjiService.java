package com.naver.service;

import java.util.List;

import com.naver.vo.GongjiVO;

public interface GongjiService {

	List<GongjiVO> getGongjiList();
	GongjiVO getGcont(int gongji_no);
}
