--게시판
create table hwboard(
 board_num number not null,
 title varchar2(90) not null,
 content clob not null,
 hit number(8) default 0 not null,
 reg_date date default sysdate not null,
 modify_date date,
 filename varchar2(200),
 ip varchar2(40) not null,
 mem_num number not null,
 constraint hwboard_pk primary key (board_num),
 constraint hwboard_fk foreign key (mem_num) references hwmember (mem_num)
);

create sequence hwboard_seq;

--게시판 좋아요
create table hwboard_fav(
 board_num number not null,
 mem_num number not null,
 constraint fav_hwboard_fk1 foreign key (board_num) references hwboard(board_num),
 constraint fav_hwmember_fk2 foreign key (mem_num) references hwmember(mem_num)
);

--게시판 댓글
create table hwboard_reply(
 re_num number not null,
 re_content varchar2(900) not null,
 re_date date default SYSDATE not null,
 re_mdate date,
 re_ip varchar2(40) not null,
 board_num number not null,
 mem_num number not null,
 constraint hwboard_reply_pk primary key (re_num),
 constraint hwboard_reply_fk1 foreign key (board_num) references hwboard (board_num),
 constraint hwboard_reply_fk2 foreign key (mem_num) references hwmember (mem_num)
);

create sequence hwreply_seq;