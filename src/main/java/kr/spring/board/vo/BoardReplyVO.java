package kr.spring.board.vo;

import kr.spring.util.DurationFromNow;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardReplyVO {
	private int re_num;
	private String re_content;
	private String re_date;
	private String re_mdate; //연원일시분초로 하고싶으면 date로, 여기선 하루전, 이틀전 변환작업을 할 것이기 때문에
	private String re_ip;
	private int board_num;
	private int mem_num; 
	
	private String email;
	private String name;
	
	public void setRe_date(String re_date) {
		this.re_date = DurationFromNow.getTimeDiffLabel(re_date);
	}
	
	public void setRe_mdate(String re_mdate) {
		this.re_mdate = DurationFromNow.getTimeDiffLabel(re_mdate);
	}
	
}
