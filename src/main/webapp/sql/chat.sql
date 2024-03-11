--채팅방
create table hw_chatRoom(
 chatRoom_num number not null PRIMARY KEY, --채팅방 번호
 sender number not null, --채팅 건 사람 식별번호
 receiver number not null, --채팅 받은 사람 식별번호
 constraint hw_chatRoom_fk1 foreign key (sender) references hwmember (mem_num),
 constraint hw_chatRoom_fk2 foreign key (receiver) references hwmember (mem_num)
);
create sequence hw_chatRoom_seq;
--채팅글
create table hw_chat(
 chat_num number not null primary key, --채팅글 고유번호
 chatRoom_num number not null, --채팅방 고유번호
 mem_num number not null, --사용자 고유번호
 chat_message varchar2(900) not null, --체팅 내용
 chat_regDate date default sysdate not null, -- 채팅 날짜
 chat_readCheck number(1) not null, --읽음 상태 (0:읽음, 1:읽지 않음)
 constraint hw_chat_fk1 foreign key (chatRoom_num) references hw_chatRoom (chatRoom_num),
 constraint hw_chat_fk2 foreign key (mem_num) references hwmember (mem_num)
);
create sequence hw_chat_seq;