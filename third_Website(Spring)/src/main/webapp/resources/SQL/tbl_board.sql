--tbl_member 테이블 생성
create table tbl_member(
	userid varchar2(30) primary key --회원 아이디
	,userpw varchar2(50) not null --비번
	,username varchar2(50) not null --회원이름
	,email varchar2(200) --email;
	,regdate date--가입날짜
	,updatedate date --수정날짜
);

select * from tbl_member;

--스프링 MVC 게시판 테이블 생성
create table tbl_board(
	bno number(38) primary key --게시판 번호
	,writer varchar2(50) not null --작성자
	,title varchar2(200) not null --제목
	,content varchar2(4000) --글 내용
	,viewcnt int default 0 --조회수, default 0 제약조건은 해당 컬럼에 굳이 레코드를 저장하지 않아도 기본 정수값 0이 저장됨.
	,regdate date --등록날짜
);

select * from tbl_board order by bno desc;

--댓글 수를 카운트해서 저장하는 replycnt컬럼을 추가
alter table tbl_board add (replycnt number(38) default 0); --댓글이 추가되면 +1, 댓글이 삭제되면 -1

--tbl_reply 테이블의 게시물 번호에 해당하는 댓글수를 카운터해서 tbl_board테이블의 replycnt컬럼 댓글수 값을 변경시킴.
update tbl_board set replycnt=(select count(rno) from tbl_reply where bno=tbl_board.bno) where bno>0;

--bno_seq 시퀀스 생성
create sequence bno_seq
start with 1 --1부터 시작
increment by 1 --1씩 증가
nocache; --임시 메모리(저장소) 사용 안함.

--bno_seq시퀀스 다음번호 값 확인
select bno_seq.nextval from dual;

--댓글 테이블 생성
create table tbl_reply(
	rno number(38) primary key --댓글번호
	,bno number(38) default 0 --tbl_board테이블의 게시판 번호값만 저장됨. 외래키 제약조건으로 추가 설정.
	--default 0제약조건은 굳이 해당 컬럼에 레코드를 저장하지 않아도 기본값 0이 저장된다.
	,replyer varchar2(100) not null --댓글작성자
	,replytext varchar2(4000) not null --댓글내용
	,regdate date--댓글등록날짜
	,updatedate date--댓글수정날짜
);

select * from tbl_reply order by rno desc;

--외래키 설정
alter table tbl_reply add constraint tbl_reply_bno_fk --외래키 제약조건명
foreign key(bno) references tbl_board(bno);
--foreign key(외래키)

--댓글 시퀀스 생성
create sequence rno_seq
start with 1
increment by 1
nocache;

--rno_seq 시퀀스 다음 번호값 확인
select rno_seq.nextval from dual;

--tbl_user 테이블 생성
create table tbl_user(
	uid2 varchar2(50) primary key --회원아이디
	,upw varchar2(50) not null --비번
	,uname varchar2(50) not null --회원이름
	,upoint number(38) default 0 --메시지가 보내지면 포인터 점수 10점이 업
);

insert into tbl_user (uid2,upw,uname) values ('user00','user00','홍길동');
insert into tbl_user (uid2,upw,uname) values ('user01','user01','이순신');

select * from tbl_user;

--tbl_message 테이블 생성
create table tbl_message(
	mid number(38) primary key 
	,targetid varchar2(50) not null --외래키 제약조건 추가 설정, tbl_user테이블의 uid2 컬럼 아이디값만 가져와 저장
	,sender varchar2(50) not null --메시지를 보낸 사람
	,message varchar2(1000) not null --보낸 메시지 내용
	,senddate date --메시지를 보낸 날짜
);

--외래키 제약조건 추가설정
alter table tbl_message add constraint tbl_message_targetid_fk
foreign key (targetid) references tbl_user(uid2);

--mid_no_seq 시퀀스 생성
create sequence mid_no_seq 
start with 1
increment by 1
nocache;

--mid_no_seq 시퀀스 다음번호 값 확인
select mid_no_seq.nextval from dual;

select * from tbl_message;

delete from tbl_message where mid=3;

--시험에서 필요한 tbl_boardNight
create table tbl_boardNight(
	num number(30) primary key --게시판 번호
	,writer varchar2(50) not null --작성자
	,title varchar2(200) not null --제목
	,regdate date --등록날짜
);

--시퀀스 생성
create sequence num_seq
start with 1
increment by 1
nocache;

insert into tbl_boardNight values (num_seq.nextval,'2작성자','2작성제목',sysdate);

select * from tbl_boardNight;