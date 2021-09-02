--insert문 실습을 위한 테이블을 생성
create table emp26(
	empno number(38) primary key --사원번호
	,ename varchar2(100) --사원명
	,LOC varchar2(50)--사는지역
);

--원하는 컬럼을 명시해서 레코드 저장
insert into emp26 (LOC,ename,empno) values('서울','홍길동',101);

select * from emp26;

--컬럼명을 생략하고 레코드 저장 >>모든 컬럼명에 대한 데이터를 추가해야 가능
insert into emp26 values(102,'이순신','서울');

--insert all문 실습을 위한 테이블 생성
create table emp27(
	empno number(38) primary key --사원번호
	,ename varchar2(30)--사원명
	,sal number(38)--급여
	,LOC varchar2(50) 
	,dept int --부서번호
);

insert into emp27 values(101,'강감찬',1000,'서울시 동작구',10);
insert into emp27 values(102,'이순신',1500,'서울시 서초구',20);
insert into emp27 values(103,'세종대왕님',2000,'서울시 양천구',20);

select * from emp27 order by empno asc;

--조건식을 거짓으로 해서 사원번호, 사원명, 급여 컬럼만 복제하고 레코드는 복사하지 않는다.
create table emp28
as 
select empno,ename,sal from emp27 where 10=0;

drop table emp28;
select * from emp28;

--사원번호, 사원명, 사는 지역 컬럼만 복제
create table emp29 as select empno,ename,LOC from emp27 where 10=0;

select * from emp29;

--하나이상의 테이블에 다중행 레코드 실습
insert all
into emp28 values(empno,ename,sal)
into emp29 values(empno,ename,LOC)
select empno,ename,sal,LOC from emp27 where dept=20;

/*emp27원본 테이블에는 부서번호가 20번에 해당하는 사원번호, 사원명, 급여, 사는지역을 검색해서
 *emp28 테이블에는 사원번호,사원명, 급여를 저장하고 emp29 테이블에는 사원번호,사원명,사는지역을 동시에 다중행 저장한다.
 */ 

select * from emp28 order by empno asc;
select * from emp29 order by empno asc;

--emp30 복제테이블 생성
create table emp30 as select empno,ename,dept from emp27 where 10=0; --조건식 거짓
select * from emp30;

--emp31 복제테이블 생성
create table emp31 as select empno,ename,sal from emp27 where 10=0;
select * from emp31;

select * from emp27 order by empno asc;

--조건에 맞는 레코드만 복수개의 테이블에 다중행 레코드 저장 실습
insert all
when sal >= 1000 then--급여가 1000이상인 경우
into emp31 values(empno,ename,sal)
when dept=20	then --부서번호가 20인 경우
into emp30 values(empno,ename,dept)
select empno,ename,sal,dept from emp27;

select * from emp30 order by empno asc;
select * from emp31 order by empno asc;

