package kr.spring.hwchat.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import kr.spring.hwchat.vo.ChatRoomVO;
import kr.spring.hwchat.vo.ChatVO;

public interface HwChatService {

	/*===================================
	 * 				채팅방 설정 
	 ===================================*/
	
	//채팅방 번호 조회
	public int selectChatRoom(Map<String,Object> map);
	//채팅방 존재유무 확인(0:없음,1:존재)
	public int selectChatRoomCheck(Map<String,Object> map);
	//채팅방 생성
	public void insertChatRoom(Map<String,Object> map);
	//사용자의 채팅방 리스트 출력
	public List<ChatRoomVO> selectChatRoomList(int mem_num);
	//채팅방의 참여자 구하기
	public ChatRoomVO selectChatRoomDetail(int chatroom_num);
	
	/*===================================
	 * 				채팅 설정 
	 ===================================*/
	
	//채팅 추가
	public void insertChat(ChatVO chatVO);
	//채팅 내역 조회
	public List<ChatVO> selectChatDetail(int chatRoom_num);
	//읽지 않은 채팅 갯수 구하기
	public int selectCountChat(Map<String,Object> map); 
	//채팅 읽음처리
	public void updateChatRead(Map<String,Object> map);
	
	//채팅 삭제
	public void deleteChat(int chat_num);
	//채팅방 삭제
	public void deleteChatRoom(int chatroom_num);
	
}
