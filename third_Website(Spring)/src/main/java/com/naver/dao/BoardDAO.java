package com.naver.dao;

import java.util.List;

import com.naver.vo.BoardVO;

public interface BoardDAO {

	void insertBoard(BoardVO b);

	int getTotalCount();

	List<BoardVO> getBoardList(BoardVO b);

	void updateHit(int bno);

	BoardVO getCont(int bno);

	void editBoard(BoardVO eb);

	void delBoard(int bno);

	void updateReplyCnt(int bno, int count);

}
