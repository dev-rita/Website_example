package com.naver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naver.dao.GongjiDAO;
import com.naver.vo.GongjiVO;

@Service
public class GongjiServiceImpl implements GongjiService {

	@Autowired
	private GongjiDAO gongjiDao;

	@Override
	public List<GongjiVO> getGongjiList() {
		return this.gongjiDao.getGongjiList();
	}

	@Transactional // 스프링의 aop를 통한 트랜잭션을 적용함으로써 사이트의 신뢰도와 데이터 불일치 현상을 제거
	@Override
	public GongjiVO getGcont(int gongji_no) {
		this.gongjiDao.updateHit(gongji_no);//조회수 증가
		return this.gongjiDao.getGCont(gongji_no);//번호에 해당하는 내용을 가져옴.
	}
}
