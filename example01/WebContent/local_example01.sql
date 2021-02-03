create table memo (
no number not null ,
writer varchar2(50) not null ,
content varchar2(50) not null ,
regiDate timestamp default current_timestamp ,
primary key(no)
);

create sequence seq_memo start with 1 increment by 1 nomaxvalue nocache;