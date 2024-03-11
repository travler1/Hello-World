package kr.spring.hwchat.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.hwchat.dao.HwChatMapper;
import kr.spring.hwchat.vo.ChatRoomVO;
import kr.spring.hwchat.vo.ChatVO;

@Service
@Transactional
public class HwChatServiceImpl implements HwChatService{
	
	@Autowired
	HwChatMapper hwChatMapper;

	@Override
	public int selectChatRoom(Map<String, Object> map) {
		return hwChatMapper.selectChatRoom(map);
	}

	@Override
	public int selectChatRoomCheck(Map<String, Object> map) {
		return hwChatMapper.selectChatRoomCheck(map);
	}

	@Override
	public void insertChatRoom(Map<String, Object> map) {
		hwChatMapper.insertChatRoom(map);
	}

	@Override
	public List<ChatRoomVO> selectChatRoomList(int mem_num) {
		return hwChatMapper.selectChatRoomList(mem_num);
	}

	@Override
	public ChatRoomVO selectChatRoomDetail(int chatroom_num) {
		return hwChatMapper.selectChatRoomDetail(chatroom_num);
	}

	@Override
	public void insertChat(ChatVO chatVO) {
		hwChatMapper.insertChat(chatVO);
	}

	@Override
	public List<ChatVO> selectChatDetail(int chatRoom_num) {
		return hwChatMapper.selectChatDetail(chatRoom_num);
	}

	@Override
	public int selectCountChat(Map<String, Object> map) {
		return hwChatMapper.selectCountChat(map);
	}

	@Override
	public void updateChatRead(Map<String, Object> map) {
	}

	@Override
	public void deleteChat(int chat_num) {
	}

	@Override
	public void deleteChatRoom(int chatroom_num) {
	}
	
	
}
