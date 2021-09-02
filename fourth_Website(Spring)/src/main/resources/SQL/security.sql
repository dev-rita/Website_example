--users 테이블 생성
create table users(
	username varchar2(50) primary key --아이디
	,password varchar2(50) not null --비번
	,enabled char(1) default '1' --char는 고정문자형 varchar는 가변문자형
);

select * from users;

create table authorities(
	username varchar2(50) not null --회원 아이디
	,authority varchar2(50) not null --아이디에 권한 부여
	,constraint authorities_username_fk foreign key(username) references users(username)
	--외래키 설정, 외래키 컬럼 username은 주인 테이블의 부모의 users 테이블의 username컬럼을 참조하고 있다.
	--users 테이블의 회원 아이디값만 외래키로 설정된 컬럼의 아이디로 저장된다.
);

/*
 * 고유 인덱스 생성 문법 형식)
 * create unique index 인덱스명 on 테이블명(컬럼명);
 */

create unique index ix_auth_username on authorities(username,authority);

insert into users (username,password) values('user00','pw00');
insert into users (username,password) values('member00','pw00');
insert into users (username,password) values('admin00','pw00');

insert into authorities (username,authority) values('user00','ROLE_USER');
insert into authorities (username,authority) values('member00','ROLE_MANAGER');
insert into authorities (username,authority) values('admin00','ROLE_MANAGER');
insert into authorities (username,authority) values('admin00','ROLE_ADMIN');

select * from authorities order by authority asc;

--tbl_member1 테이블 생성
create table tbl_member1(
	userid varchar2(50) primary key -- 회원 아이디
	,userpw varchar2(100) not null --비번
	,username varchar2(50) not null --회원 이름
	,regdate date default sysdate --가입날짜
	,updatedate date default sysdate --수정날짜
	,enabled char(1) default '1'
);

select * from tbl_member1 order by userid asc;

--권한부여 테이블
create table tbl_member1_auth(
	userid varchar2(50) not null --아이디
	,auth varchar2(50) not null --권한부여
	,constraint tbl_member1_auth_userid_fk foreign key(userid) references tbl_member1(userid) --외래키 설정
);

select * from tbl_member1_auth order by userid asc;

--스프링 시큐리티 자동 로그인 정보를 유지하는 테이블
create table persistent_logins(
	username varchar2(64) not null --회원 아이디
	,series varchar2(64) primary key --비번
	,token varchar2(64) not null --토큰 정보
	,last_used timestamp not null --로그인한 날짜 시간
);

select * from persistent_logins;