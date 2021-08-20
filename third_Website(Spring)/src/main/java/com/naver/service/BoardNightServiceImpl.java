package com.naver.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.naver.dao.BoardNightDAO;
import com.naver.vo.BoardNightVO;

@Service
public class BoardNightServiceImpl implements BoardNightService {

	@Inject
	private BoardNightDAO boardNightDao;
	
	@Override
	public void insertBoard(BoardNightVO nb) {
		this.boardNightDao.insertBoard(nb);
	}

	@Override
	public int getTotalCount() {
		return this.boardNightDao.getTotalCount();
	}

}
