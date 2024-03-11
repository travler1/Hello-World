package kr.spring.matching.vo;

import java.sql.Date;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
@Getter
@ToString
public class LetterVO {
	int letter_num;
	int sender;
	int receiver;
	@NotBlank
	String letter_content;
	Date date_sent;
	Date date_read;
	int send_del;
	int receive_del;
	String letter_ip;
	String letter_status;
	
	String id;
}
