package kr.spring.matching.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.spring.matching.service.MatchingService;
import kr.spring.matching.vo.AdviceVO;
import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.FileUtil;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AdviceController {

	@Autowired
	MatchingService matchingService;

	@Autowired
	MemberService memberService;
	
	@ModelAttribute
	public AdviceVO init() {
		return new AdviceVO();
	}
	
	//첨삭 보내기
	@GetMapping(value = {"/matching/send_advice", "/myPage/send_advice"})
	public String send_advice(HttpServletRequest request, HttpSession session, 
			int user_id, Model model) {

		MemberVO user = (MemberVO)session.getAttribute("user");
		log.debug("<<MemberVO (login)>> : " + user);
		log.debug("<<user_id mem_num >> : " + user_id);

		if(user==null) {//로그인 되지 않은 경우
			model.addAttribute("message", "회원만 이용가능합니다.");
			model.addAttribute("url", request.getContextPath() + "/main/login");

			return "matching/resultAlert";
		}else {//로그인 된 경우
			//받은 첨삭유무 확인
			if(matchingService.AdviceCheck(user_id, user.getMem_num())!=null) {
				model.addAttribute("answerCheck",1);
			}
			model.addAttribute("login_user", user);
			model.addAttribute("receive_user", memberService.selectMember(user_id));
		}

		return "matching/advice/send_advice";
	}
	
	//첨삭 답장하기
	@PostMapping(value={"matching/send_advice","myPage/send_advice"})
	public String sendletter_result(HttpServletRequest request, HttpSession session, 
			Model model, @Valid AdviceVO adviceVO, BindingResult result, int sender, int receiver) throws IllegalStateException, IOException {

		MemberVO user = (MemberVO)session.getAttribute("user");

		if(user==null) {
			model.addAttribute("message", "회원만 이용 가능합니다.");
			model.addAttribute("url", request.getContextPath() + "/main/login");
		}else {
			if(result.hasErrors()) {
				int user_id = Integer.parseInt((String)result.getFieldValue("receiver"));
				return send_advice(request, session, user_id, model);
			}
			
			adviceVO.setSender(sender);
			adviceVO.setReceiver(receiver);
			adviceVO.setAdvice_ip(request.getRemoteAddr());
			adviceVO.setFilename(FileUtil.createFile(request, adviceVO.getUpload()));
			
			matchingService.insertAdvice(adviceVO);
			
			if(matchingService.AdviceCheck(receiver, sender)!=null) {
				matchingService.completeAdvice(sender, receiver);
				model.addAttribute("message", "첨삭 응답이 성공적으로 전송되었습니다.");
				model.addAttribute("url", request.getContextPath() + "myPage/myAdvice");
			}else {
			model.addAttribute("message", "첨삭 요청이 성공적으로 전송되었습니다.");
			model.addAttribute("url", request.getContextPath() + "myPage/myAdvice");
			}
		}

		return "matching/resultAlert";
	}
	
	
}
