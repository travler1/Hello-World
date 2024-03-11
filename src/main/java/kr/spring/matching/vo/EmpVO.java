package kr.spring.matching.vo;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmpVO {
	
	private int emp_num;
	private int emp_status;
	private String comsize;
	private String compeople;
	private String field;
	private String role;
	private String career;
	private String salary_status;
	private String salary;
	private String periodtime;
	private String education;
	private String major;
	private String certification;
	private String location;
	private String location_api;
	private String location_api_lat;
	private String location_api_lng;
	private String workstart;
	private Date register_date;
	private Date m_date;
	
	private int mem_num;
	private String filename;
	private MultipartFile upload;
	private String advice;
	private int auth;
	private String name;
	private String email;
	
}
