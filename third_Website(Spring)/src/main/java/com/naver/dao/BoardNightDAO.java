package com.naver.dao;

import com.naver.vo.BoardNightVO;

public interface BoardNightDAO {

	void insertBoard(BoardNightVO nb);

	int getTotalCount();
}
