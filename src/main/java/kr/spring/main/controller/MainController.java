package kr.spring.main.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//import kr.spring.member.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {
	
	@GetMapping("/")
	public String init(HttpSession session) {
	//	MemberVO user = (MemberVO)session.getAttribute("user");
		//관리자로 로그인하면 관리자 메인으로 이동 처리
		//나중에 고치는 게 좋음. 관리자일 경우 관리자 화면으로 가는 게 좋음.
	//	if(user!=null && user.getAuth()==9) {
	//		return "redirect:/main/admin";
	//	}
		
		return "redirect:/main/main";
	}
	
	@GetMapping("/main/main")
	public String main(Model model) {
		
		log.debug("<<메인 실행>>");
		
		return "main"; //타일스 설정명
	}
	
	
}
