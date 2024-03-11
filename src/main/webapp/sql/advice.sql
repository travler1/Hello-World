create table advice(
	advice_num number not null,
	sender number not null,
	receiver number not null,
	advice_content clob not null,
	date_sent date not null,
	date_read date,
	receive_read number,
	receive_del number default 0,
	sent_del number default 0,
	advice_complete number default 0,
	advice_ip varchar2(30) not null,
	filename varchar2(300) not null,
	constraint advice_pk primary key (advice_num),
	constraint advice_fk1 foreign key (sender) references hwmember(mem_num),
	constraint advice_fk2 foreign key (receiver) references hwmember(mem_num)
);

create sequence advice_seq;