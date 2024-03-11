package kr.spring.hwchat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.hwchat.service.HwChatService;
import kr.spring.hwchat.vo.ChatVO;
import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HwChatController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	HwChatService hwChatService;
	
	/*=================================
	 * 		 채팅방 조회 및 생성
	 *=================================*/
	
	//채팅창 생성 (채팅방 번호 (생성/조회) , 채팅방 번호 넘겨주기)
	@GetMapping("matching/make_chat")
	public String make_chatRoom(HttpSession session, Model model, HttpServletRequest request, int user_num) {
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		log.debug("<<MemberVO (login)>> : " + user);

		if(user==null) {//로그인 되지 않은 경우
			model.addAttribute("message", "회원만 이용가능합니다.");
			model.addAttribute("url", request.getContextPath() + "/main/login");

			return "matching/resultAlert";
		}
		
		//채팅상대방,로그인유저 정보 담은 map 출력
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sender", user.getMem_num());
		map.put("receiver", user_num);
		
		//기존 채팅방 존재 확인 (0:없음,1:존재)
		int check = hwChatService.selectChatRoomCheck(map);
		log.debug("<<check value >> : " + check);
		log.debug("<<>>");
		
		if(check==0) {
			//채팅방 생성
			hwChatService.insertChatRoom(map);
		}
		//채팅방 번호 구하기
		int chatRoom_num = hwChatService.selectChatRoomCheck(map);
		log.debug("<<chatRoom_num >> : " + chatRoom_num);
		
		model.addAttribute("chatRoom_num", chatRoom_num);
		model.addAttribute("receiver", user_num);
		model.addAttribute("sender", user.getMem_num());
		
		return "matching/chat/chatDetail";
	}
	
	/*=================================
	 * 			 채팅메시지 조회
	 *=================================*/
	//채팅 메시지 페이지 호출
		@RequestMapping("/chat/chatDetailAjax")
		@ResponseBody
		public Map<String,Object> talkDetail(@RequestParam int chatRoom_num,HttpSession session) {
			
			//ajax로 반환할 결과를 담을 map
			Map<String,Object> mapAjax = new HashMap<String,Object>();
			
			MemberVO user = (MemberVO)session.getAttribute("user");
			if(user==null) {//로그인이 되지 않은 경우
				mapAjax.put("result", "logout");
			}else {//로그인 된 경우
				
				//채팅내역조회를 위한 map
				Map<String,Integer> map = new HashMap<String,Integer>();
				map.put("chatRoom_num", chatRoom_num);
				
				List<ChatVO> list = hwChatService.selectChatDetail(chatRoom_num);
				
				mapAjax.put("result", "success");
				mapAjax.put("list", list);
				mapAjax.put("login_user", user.getMem_num());			
			}		
			
			return mapAjax;
		}
		
		//채팅 메시지 전송
		@RequestMapping("/chat/writeChatAjax")
		@ResponseBody
		public Map<String,Object> writeChatAjax(ChatVO chatVO, HttpSession session){

			Map<String,Object> mapAjax = new HashMap<String,Object>();

			MemberVO user = (MemberVO)session.getAttribute("user");
			
			if(user==null) {//로그인이 되지 않은 경우
				mapAjax.put("result", "logout");
			}else {//로그인 된 경우
				chatVO.setMem_num(user.getMem_num());

				log.debug("<<채팅 메시지 등록 TalkVO>> : " + chatVO);
				hwChatService.insertChat(chatVO);

				mapAjax.put("result", "success");
			}
			return mapAjax;
		}
	
		/*====================================
	      * 	 채팅방 삭제 및 채팅 내역 삭제
	      *===================================*/
		 @RequestMapping("/chat/deleteChatRoom")
		 @ResponseBody
		 public Map<String,Object> deleteChatRoom(HttpSession session,@RequestParam int chatRoom_num) {
			 Map<String,Object> mapAjax = new HashMap<String,Object>();
				
				MemberVO user = (MemberVO)session.getAttribute("user");
				if(user==null) {//로그인이 되지 않은 경우
					mapAjax.put("result", "logout");
				}else {//로그인 된 경우
				    hwChatService.deleteChatRoom(chatRoom_num);//채팅방,채팅내역 삭제
					mapAjax.put("result", "success");
				}
				return mapAjax;
		 }
}
