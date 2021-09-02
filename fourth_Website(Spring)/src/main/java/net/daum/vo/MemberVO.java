package net.daum.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data//setter(), getter() 자동 제공
public class MemberVO {
	private String userid;
	private String userpw;
	private String userName;
	private boolean enabled;
	
	private Date regDate;
	private Date updateDate;
	private List<AuthVO> authList;//권한 목록
}
