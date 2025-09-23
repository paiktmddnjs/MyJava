CREATE TABLE test (
    tno   NUMBER PRIMARY KEY,
    tname VARCHAR2(100) NOT NULL,
    tdate DATE DEFAULT sysdate NOT NULL
);
commit;


select * from test;

INSERT INTO test VALUES (1, '백승원', '2002-03-24');

select * from member;


CREATE USER C##PROJECT1 IDENTIFIED BY PROJECT1;

-- 접속 권한 및 테이블 생성 권한 부여
GRANT CONNECT, RESOURCE TO C##PROJECT1;



​
