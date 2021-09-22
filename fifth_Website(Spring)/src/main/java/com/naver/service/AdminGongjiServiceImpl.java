package com.naver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.dao.AdminGongjiDAO;
import com.naver.vo.GongjiVO;

@Service
public class AdminGongjiServiceImpl implements AdminGongjiService {

	@Autowired
	private AdminGongjiDAO adminGongjiDao;

	@Override
	public int getListCount(GongjiVO g) {
		return this.adminGongjiDao.getListCount(g);
	}

	@Override
	public List<GongjiVO> getGongjiList(GongjiVO g) {
		return this.adminGongjiDao.getGongjiList(g);
	}

	@Override
	public void insertG(GongjiVO g) {
		this.adminGongjiDao.insertG(g);		
	}

	@Override
	public GongjiVO getGongjiCont(int no) {
		return this.adminGongjiDao.getGongjiCont(no);
	}

	@Override
	public void updateGongji(GongjiVO g) {
		this.adminGongjiDao.updateGongji(g);		
	}

	@Override
	public void deleteGongji(int no) {
		this.adminGongjiDao.deleteGongji(no);		
	}		
}
