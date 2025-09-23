create table member (
user_no number primary key,
user_Id varchar2(30) not null unique,
user_pwd varchar2(30) not null,
user_name varchar2(20) not null,
Gender char(1) check(gender in ('M','F')),
age number,
email varchar2(30),
phone char(11),
address varchar2(100),
hobby varchar2(50),
enroll_date date default sysdate not null
);

create sequence seq_user_no nocache;
commit;

select * from member;