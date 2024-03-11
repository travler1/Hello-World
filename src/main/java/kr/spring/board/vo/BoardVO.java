package kr.spring.board.vo;

import java.sql.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardVO {
	private int board_num;
	@NotBlank
	private String title;
	@NotBlank
	private String content;
	private int hit;
	private Date reg_date;
	private Date modify_date;
	private MultipartFile upload; //form에선 upload로 가지고 있다가 넣어줄 땐 filename에?. upload가 파일정보를 갖게 됨. 얘를 통해서 filename을 만들어줌. 실제로 저장되는건 filename만.
	private String filename;
	private String ip;
	private int mem_num;
	
	private String email;
	private String name;
	
	private int re_cnt;//댓글 개수
	private int fav_cnt;//좋아요 개수
}
