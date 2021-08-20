package com.naver.service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naver.dao.MessageDAO;
import com.naver.dao.PointDAO;
import com.naver.vo.MessageVO;

@Service
public class MessageServiceImpl implements MessageService {
	//ServiceImpl에서 고객의 추가요구사항을 반영하고, 가장 중요한 스프링의 AOP를 통한 트랜잭션을 적용함.
	//>>데이터 일치성 보장,사이트 신뢰도 상승

	@Autowired
	private MessageDAO messageDao;
	
	@Inject//자동의존성 주입
	private PointDAO pointDao;
	
	//스프링의 AOP를 통한 트랜잭션 적용 대상
	@Transactional //트랜잭션 적용(update취소 -> create 취소)
	@Override
	public void addMessage(MessageVO vo) {
		this.messageDao.create(vo);
		this.pointDao.updatePoint(vo.getSender(),10);//메시지를 보낸사람에게 10점 업
	}
}
