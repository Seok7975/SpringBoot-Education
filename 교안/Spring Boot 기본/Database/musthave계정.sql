-- system 계정
alter session set "_ORACLE_SCRIPT"=true;

CREATE USER musthave IDENTIFIED BY 1234;

-- 권한부여시 테이블 스페이스 권한을 추가로 부여해야한다. 
GRANT connect, resource, unlimited tablespace TO musthave;

-- 테이블 스페이스 조회 확인
SELECT tablespace_name, status, contents FROM dba_tablespaces;

-- 테이블 스페이스별 가용 공간 확인
SELECT tablespace_name, sum(bytes), max(bytes) from dba_free_space
    GROUP BY tablespace_name;

-- musthave 계정 사용하는 테이블 스페이스 확인
SELECT username, default_tablespace  FROM dba_users
    WHERE username in upper('musthave');

-- musthave 계정에 users 테이블 스페이스에 데이터를 입력할 수 있도록 
-- 5m 의 용량을 할당.
ALTER USER musthave QUOTA 5m ON users;

--- musthave 계정
SELECT * FROM tab;

DROP TABLE member;
DROP TABLE board;
DROP TABLE seq_board_num;

-- 회원 테이블 만들기
create table member(
    id varchar2(10) not null, 
    pass varchar2(10) not null,
    name varchar2(30) not null,
    regidate date default sysdate not null,
    primary key (id)
);

-- 모델1 방식의 게시판 테이블 만들기
create table board(
    num number primary key,
    title varchar2(200) not null,
    content varchar2(2000) not null,
    id varchar2(10) not null,
    postdate date default sysdate not null,
    visitcount number(6)
);

-- 외래키 설정
alter table board
    add constraint board_mem_fk foreign key (id)
    references member(id);

--  시퀀스 생성
create sequence seq_board_num
    increment by 1  -- 1씩 증가
    start with 1    -- 시작값 1
    minvalue 1      -- 최소값 1
    nomaxvalue      -- 최대값은 무한대
    nocycle         -- 순환하지 않음.
    nocache;        -- 캐시 안 함.

-- 더미 데이터 입력
insert into member (id, pass, name) values ('musthave', '1234', '머스트헤브');
-- 아래를 먼저 입력하면 에러남 => 무결성 제약조건(MUSTHAVE.BOARD_MEM_FK)이 
-- 위배되었습니다- 부모 키가 없습니다. 그래서 위테이블 먼저 입력
insert into board (num, title, content, id, postdate, visitcount)
    values (seq_board_num.nextval, '제목1입니다.', '내용1입니다.', 'musthave', sysdate, 0);
INSERT INTO board VALUES (seq_board_num.nextval, '지금은 봄입니다', '봄의왈츠', 'musthave', sysdate, 0);
INSERT INTO board VALUES (seq_board_num.nextval, '지금은 여름입니다', '여름향기', 'musthave', sysdate, 0);
INSERT INTO board VALUES (seq_board_num.nextval, '지금은 가을입니다', '가을동화', 'musthave', sysdate, 0);
INSERT INTO board VALUES (seq_board_num.nextval, '지금은 겨울입니다', '겨울연가', 'musthave', sysdate, 0);
commit;

-- 9장 페이징
DESC member;

SELECT * FROM member;
SELECT id, pass, rownum FROM member;

SELECT * 
 FROM (SELECT Tb.*, rownum rNum 
        FROM (SELECT * FROM board ORDER BY num DESC) Tb)
 WHERE rNum BETWEEN 1 AND 10;

--  13장 파일업로드
DROP TABLE myfile;
create table myfile (
    idx number primary key,
    title varchar2(200) not null,
    cate varchar2(100),
    ofile varchar2(100) not null,
    sfile varchar2(30) not null,
    postdate date default sysdate not null
);
DESC myfile;

DELETE * FROM myfile;
SELECT * FROM myfile;

-- 14장 모델2 방식의 파일첨부형 게시판 테이블 생성
DROP TABLE mvcboard;
create table mvcboard (
	idx number primary key, 
	name varchar2(50) not null, 
	title varchar2(200) not null, 
	content varchar2(2000) not null, 
	postdate date default sysdate not null, 
	ofile varchar2(200), 
	sfile varchar2(30), 
	downcount number(5) default 0 not null, 
	pass varchar2(50) not null, 
	visitcount number default 0 not null
);

DESC mvcboard;

insert into mvcboard (idx, name, title, content, pass)
    values (seq_board_num.nextval, '김유신', '자료실 제목1 입니다.','내용','1234');
insert into mvcboard (idx, name, title, content, pass)
    values (seq_board_num.nextval, '장보고', '자료실 제목2 입니다.','내용','1234');
insert into mvcboard (idx, name, title, content, pass)
    values (seq_board_num.nextval, '이순신', '자료실 제목3 입니다.','내용','1234');
insert into mvcboard (idx, name, title, content, pass)
    values (seq_board_num.nextval, '강감찬', '자료실 제목4 입니다.','내용','1234');
insert into mvcboard (idx, name, title, content, pass)
    values (seq_board_num.nextval, '대조영', '자료실 제목5 입니다.','내용','1234');

SELECT * FROM mvcboard;
commit;











