package kr.spring.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MyPageController {
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/member/myPage")
	public String myPage(HttpSession session, Model model) {
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		MemberVO member = memberService.selectMember(user.getMem_num());
		
		log.debug("<<로그인 회원 정보 >> : " + member);
		
		model.addAttribute("member",member);
		
		return "myPage";
	}
}
