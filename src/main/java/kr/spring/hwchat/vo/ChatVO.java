package kr.spring.hwchat.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChatVO {
	private int chat_num;
	private int chatRoom_num;
	private int mem_num;
	private String name;
	private String chat_message;
	private String chat_regdate;
	private int chat_readCheck; //읽음 상태 (0:읽음, 1:읽지 않음)
}
