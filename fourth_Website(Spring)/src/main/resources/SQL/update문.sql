--모든 행 레코드를 수정
create table emp35 as select * from emp27;

select * from emp35;

--모든 부서번호를 30으로 수정
update emp35 set dept=30;

--where 조건식으로 원하는 데이터만 수정 -> 101번 사원의 부서번호를 20으로 수정
update emp35 set dept=20 where empno=101;

--서브쿼리로 자료를 수정하기 위한 테이블 생성
create table dept(
	deptno int primary key --부서번호
	,dname varchar2(100) --부서명
	,LOC varchar2(50) --지역명
);

insert into dept values(10,'경리부','서울');
insert into dept values(20,'영업부','부산');
insert into dept values(30,'관리부','인천');
insert into dept values(40,'연구부','대전');

create table dept01 as select * from dept;

select * from dept order by deptno asc;
select * from dept01 order by deptno asc;


--서브쿼리 검색 결과자료로 레코드 수정
update dept01 set (dname,LOC)=(select dname,LOC from dept where deptno=40) where deptno=20;

--40번 부서번호 레코드 삭제
delete from dept01 where deptno=40;

select * from emp27;

--서브 쿼리로 경리부 부서번호 10을 검색해서 10에 해당하는 자료를 삭제
delete from emp27 where deptno=(select deptno from dept01 where dname='경리부')