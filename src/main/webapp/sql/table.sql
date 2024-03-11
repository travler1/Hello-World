--회원관리
create table hwmember(
 mem_num number not null,
 email varchar2(50) not null,
 auth number(1) default 2 not null, --0:탈퇴회원,1:수강생회원,2:현직자회원,3:정지회원,9:관리자
 constraint spmember_pk primary key (mem_num)
);
create table hwmember_detail(
 mem_num number not null,
 name varchar2(30),
 au_id varchar2(36) unique,
 passwd varchar2(35) not null,
 phone varchar2(15) ,
 zipcode varchar2(5) ,
 address1 varchar2(90) ,
 address2 varchar2(90) ,
 photo_name varchar2(100),
 reg_date date default sysdate not null,
 modify_date date,
 constraint hwmember_detail_pk primary key (mem_num),
 constraint hwmember_detail_fk foreign key (mem_num) references hwmember (mem_num)
);

create sequence hwmember_seq;

--게시판
create table spboard(
 board_num number not null,
 title varchar2(90) not null,
 content clob not null,
 hit number(8) default 0 not null,
 reg_date date default sysdate not null,
 modify_date date,
 filename varchar2(200),
 ip varchar2(40) not null,
 mem_num number not null,
 constraint spboard_pk primary key (board_num),
 constraint spboard_fk foreign key (mem_num) references spmember (mem_num)
);

create sequence spboard_seq;

--게시판 좋아요
create table spboard_fav(
 board_num number not null,
 mem_num number not null,
 constraint fav_spboard_fk1 foreign key (board_num) references spboard(board_num),
 constraint fav_spmember_fk2 foreign key (mem_num) references spmember(mem_num)
);

--게시판 댓글
create table spboard_reply(
 re_num number not null,
 re_content varchar2(900) not null,
 re_date date default SYSDATE not null,
 re_mdate date,
 re_ip varchar2(40) not null,
 board_num number not null,
 mem_num number not null,
 constraint spboard_reply_pk primary key (re_num),
 constraint spboard_reply_fk1 foreign key (board_num) references spboard (board_num),
 constraint spboard_reply_fk2 foreign key (mem_num) references spmember (mem_num)
);

create sequence spreply_seq;

--채팅방
create table sptalkroom(
 talkroom_num number not null,
 basic_name varchar2(900) not null, --기본 채팅방 이름
 talkroom_date date default sysdate not null,
 constraint sptalkroom_pk primary key (talkroom_num)
);
create sequence sptalkroom_seq;

--채팅방 멤버
create table sptalk_member(
 talkroom_num number not null,
 mem_num number not null,
 room_name varchar2(900) not null, -- 멤버별 채팅방 이름
 member_date date default sysdate not null,
 constraint sptalkmember_fk1 foreign key (talkroom_num) references sptalkroom(talkroom_num),
 constraint sptaklmember_fk2 foreign key (mem_num) references spmember(mem_num)
);

--채팅 메시지
create table sptalk(
 talk_num number not null,
 talkroom_num number not null, --수신 그룹
 mem_num number not null, --발신자
 message varchar2(4000) not null,
 chat_date date default sysdate not null,
 constraint sptalk_pk primary key (talk_num),
 constraint sptalk_fk1 foreign key (talkroom_num) references sptalkroom (talkroom_num),
 constraint sptalk_fk2 foreign key (mem_num) references spmember (mem_num)
);

create sequence sptalk_seq;

--채팅 메시지 확인 (1:1 채팅이면 채팅 메시지에 컬럼 하나만 넣으면 됨. 여러 명 채팅방이라 필요함)
create table sptalk_read(
 talkroom_num number not null,
 talk_num number not null,
 mem_num number not null,
 constraint read_fk1 foreign key (talkroom_num) references sptalkroom (talkroom_num),
 constraint read_fk2 foreign key (talk_num) references sptalk (talk_num),
 constraint read_fk3 foreign key (mem_num) references spmember (mem_num)
);
