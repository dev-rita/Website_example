package com.naver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naver.dao.BbsDAO;
import com.naver.vo.BbsVO;

@Service
public class BbsServiceImpl implements BbsService {

	@Autowired
	private BbsDAO bbsDao;

	@Override
	public void insertBbs(BbsVO b) {
		this.bbsDao.insertBbs(b);		
	}

	@Override
	public int getListCount(BbsVO b) {
		return this.bbsDao.getTotalCount(b);
	}

	@Override
	public List<BbsVO> getBbsList(BbsVO b) {
		return this.bbsDao.getBbsList(b);
	}

	@Transactional //트랜잭션 적용으로 데이터의 일치성과 사이트의 신뢰도가 올라간다.
	@Override
	public BbsVO getBbsCont(int bbs_no) {
		this.bbsDao.updateHit(bbs_no);//조회수 증가
		return this.bbsDao.getBbsCont(bbs_no);//번호에 해당하는 내용가져오기
	}

	@Override
	public BbsVO getBbsCont2(int bbs_no) {
		return this.bbsDao.getBbsCont(bbs_no);
	}

	@Transactional //트랜잭션 적용
	@Override
	public void replyBbs(BbsVO rb) {
		this.bbsDao.updateLevel(rb);//답변 레벨 증가
		this.bbsDao.replyBbs(rb);//답변 저장
	}

	@Override
	public void editBbs(BbsVO b) {
	    this.bbsDao.editBbs(b);		
	}

	@Override
	public void delBbs(int bbs_no) {
		this.bbsDao.delBbs(bbs_no);
	}
}






