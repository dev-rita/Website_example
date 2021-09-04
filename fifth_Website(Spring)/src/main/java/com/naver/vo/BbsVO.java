package com.naver.vo;




import lombok.Data;

@Data //setter(),getter() 메서드 등 자동제공
public class BbsVO {

	//되도록이면 테이블 컬럼명과 빈 클래스 변수명을 같게 한다.
	private int bbs_no;
	private String bbs_name;
	private String bbs_title;
	private String bbs_pwd;
	private String bbs_cont;
	private String bbs_file;
	private int bbs_hit;
	private int bbs_ref;//관리자 답글에서 원본글과 답변글을 묶어주는 그룹번호 역할
	private int bbs_step;//관리자 답글에서 원본글이면 0,첫번째 답변글이면 1, 두번째 답변글이면 2, 즉 원본글과 답변글을 구분하는 번호값이면서 몇번째 답변글인가를 알려줌.
	private int bbs_level;//관리자 답글에서 답변글 정렬순서
	private String bbs_date;
	
	//페이징 즉 쪽나누기
	private int startrow;//시작행번호
	private int endrow;//끝 행번호
	
	//검색기능과 관련
	private String find_field;//검색 필드
	private String find_name;//검색어
}
