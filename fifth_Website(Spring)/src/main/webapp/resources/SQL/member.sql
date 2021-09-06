create table member(
  mem_id varchar2(100) primary key
  ,mem_pwd varchar2(200) not null --암호화 된 비번
  ,mem_name varchar2(50) not null --회원이름
  ,mem_zip varchar2(10) not null --우편번호
  ,mem_zip2 varchar2(10) not null --우편번호
  ,mem_addr varchar2(100) not null --주소
  ,mem_addr2 varchar2(100) not null --나머지 주소
  ,mem_phone01 varchar2(10) --폰번호
  ,mem_phone02 varchar2(10) 
  ,mem_phone03 varchar2(10)
  ,mail_id varchar2(30)--메일 아이디
  ,mail_domain varchar2(100) --메일 도메인
  ,mem_date date --가입날짜
  ,mem_state number(38) --가입 회원이면 1, 탈퇴 회원이면 2
  ,mem_delcont varchar2(4000) --탈퇴 사유
  ,mem_deldate date --탈퇴 날짜
);

insert into member (mem_id,mem_pwd,mem_name,mem_zip,mem_zip2,mem_addr,mem_addr2,mem_phone01,mem_phone02,mem_phone03,
mail_id,mail_domain,mem_date,mem_state) values('aaaaa','77777','홍길동','123','789','서울시 동작구 노량진동 00빌딩','00호','010','7777',
'7777','aaaaa','daum.net',sysdate,1);

select * from member;

--우편/주소 테이블 zipcode생성
create table zipcode(
 no number(38) primary key
 ,zipcode varchar2(20) --우편번호
 ,sido varchar2(50) --시도
 ,gugun varchar2(50) --구군
 ,dong varchar2(50) --읍면동
 ,bunji varchar2(100) --번지
);

insert into zipcode values(1,'123-456','서울시','구로구','구로동','00빌딩 00호');

select * from zipcode;



