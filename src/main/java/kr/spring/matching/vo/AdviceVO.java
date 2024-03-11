package kr.spring.matching.vo;

import java.sql.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@ToString
public class AdviceVO {
	int advice_num;
	int sender;
	int receiver;
	@NotBlank
	String advice_content;
	Date date_sent;
	Date date_read;
	int receive_read;
	int receive_del;
	int sent_del;
	int advice_complete;
	String advice_ip;
	String filename;
	MultipartFile upload;
	
	String name;
}
