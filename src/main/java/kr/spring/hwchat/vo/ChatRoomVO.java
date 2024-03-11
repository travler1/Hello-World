package kr.spring.hwchat.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChatRoomVO {
	private int chatRoom_num;
	private int sender;
	private int receiver;
	private String sender_name;
	private String receiver_name;
}
