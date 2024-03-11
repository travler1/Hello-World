package kr.spring.hwchat.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.hwchat.vo.ChatRoomVO;
import kr.spring.hwchat.vo.ChatVO;

@Mapper
public interface HwChatMapper {
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
	@Select("SELECT * FROM hw_chatroom WHERE sender=#{mem_num} OR receiver=#{mem_num}")
	public List<ChatRoomVO> selectChatRoomList(int mem_num);
	//채팅방의 참여자 구하기
	@Select("SELECT * FROM hw_chatroom WHERE chatroom_num=#{chatroom_num)")
	public ChatRoomVO selectChatRoomDetail(int chatroom_num);
	
	/*===================================
	 * 				채팅 설정 
	 ===================================*/
	
	//채팅 추가
	public void insertChat(ChatVO chatVO);
	//채팅 내역 조회
	public List<ChatVO> selectChatDetail(int chatRoom_num);
	//읽지 않은 채팅 갯수 구하기
	@Select("SELECT COUNT(*) FROM hw_chat WHERE sender!=#{sender} AND chatroom_num=#{chatroom_num} AND chat_readcheck=0")
	public int selectCountChat(Map<String,Object> map); 
	//채팅 읽음처리
	public void updateChatRead(Map<String,Object> map);
	
	//채팅 삭제
	@Delete("DELETE FROM hw_chat WHERE chat_num=#{chat_num}")
	public void deleteChat(int chat_num);
	//채팅방 삭제
	@Delete("DELETE FROM hw_chatroom WHERE chatroom_num=#{chatroom_num}")
	public void deleteChatRoom(int chatroom_num);
	
}
