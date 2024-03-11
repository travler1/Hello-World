package kr.spring.member.vo;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVO {
	private int mem_num;
	private int auth;
	private String auto;
	private String au_id;
	@Email
	@NotBlank
	private String email;
	@NotBlank
	private String name;
	@Pattern(regexp="^[A-Za-z0-9]{4,12}$")
	private String passwd;
	private String phone;
	private String zipcode;
	private String address1;
	private String address2;
	private String photo_name;
	private Date reg_date;
	private Date modify_date;
	private String now_passwd;
	private MultipartFile upload;
	
	/*-------------------
	 * 비밀번호 일치 여부 체크
	 *------------------*/
	public boolean isCheckedPassword(String userPasswd) {
		if(auth!=0 && passwd.equals(userPasswd)) {
			return true;
		}
		return false;
	}
}




