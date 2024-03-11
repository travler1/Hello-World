package kr.spring.member.controller;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.AuthCheckException;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {
	
	@Autowired
	MemberService memberService;
	
	/*=========================
	 *자바빈(VO) 초기화 
	 *=========================*/
	@ModelAttribute
	public MemberVO initCommand() {
		return new MemberVO();
	}

	@GetMapping("/main/login")
	public String login(Model model) {

		log.debug("<<로그인>>");
		
		

		return "login"; //타일스 설정명
	}

	//전송된 데이터 처리
	@PostMapping("/main/login")
	public String submit(@Valid MemberVO memberVO, BindingResult result, 
			HttpSession session, HttpServletResponse response, Model model) {

		log.debug("<<회원 로그인>> : " + memberVO);

		//유효성 체크 결과 오류가 있으면 폼 호출
		//id와 passwd 필드만 유효성 체크
		if(result.hasFieldErrors("email") || result.hasFieldErrors("passwd")) {
			return login(model);
		}

		//로그인 체크(id,비밀번호 일치 여부 체크)
		MemberVO member = null;
		try {
			member = memberService.selectCheckMember(memberVO.getEmail());
			boolean check = false;
			if(member!=null) {
				//비밀번호 일치여부 체크
				check=member.isCheckedPassword(memberVO.getPasswd());
			}
			if(check) {//인증 성공
				//====자동로그인 체크 시작====//
				boolean autoLogin = memberVO.getAuto() != null && memberVO.getAuto().equals("on");
				if(autoLogin) {
					//자동로그인 체크를 한 경우
					String au_id = member.getAu_id();
					if(au_id==null) {
						//자동로그인 체크 식별값 생성
						au_id = UUID.randomUUID().toString();
						log.debug("<<au_id>> : " + au_id);
						member.setAu_id(au_id);
						//별 의미없음, au_id로 해도 됨. 뒤에 member.getMem_num과 같은 패턴으로 하려고.
						memberService.updateAu_id(member.getAu_id(),member.getMem_num());
					}

					Cookie auto_cookie = new Cookie("au-log", au_id);
					auto_cookie.setMaxAge(60*60*24*7);//쿠키의 유효 기간은 1주일
					auto_cookie.setPath("/");//쿠키를 제공할 경로 설정

					response.addCookie(auto_cookie);
				}
				//====자동로그인 체크 끝====//

				//인증 성공, 로그인 처리
				session.setAttribute("user", member);
				log.debug("<<인증 성공>>");
				log.debug("<<id>> : " + member.getEmail());
				log.debug("<<auth>> : " + member.getAuth());
				log.debug("<<au_id>> : " + member.getAu_id());

				if(member.getAuth()==9) {//관리자는 관리자 메인으로 이동
					return "redirect:/main/admin";
				}else {//일반 사용자는 사용자 메인으로 이동
					return "redirect:/main/main";
				}
			}
			//인증 실패
			throw new AuthCheckException();

		}catch(AuthCheckException e) {
			log.debug("<<인증 실패>>");
			//인증 실패 후 로그인폼 호출
			if(member!=null && member.getAuth()==0) {
				result.reject("noAuthority");
			}else {
				result.reject("invalidIdOrPassword");
			}
			return login(model);
		}

	}
	
	/*=========================
	 *		    로그아웃
	 *=========================*/
	@RequestMapping("/member/logout")
	public String processLogout(HttpSession session, HttpServletResponse response) {
		//로그아웃
		session.invalidate();
		//============로그아웃 처리 시작===============//
		//클라이언트 쿠키 처리
		Cookie auto_cookie = new Cookie("au-log","");
		log.debug("<<au-log>> : " + auto_cookie.toString());
		auto_cookie.setMaxAge(0); //쿠키 삭제
		auto_cookie.setPath("/"); //쿠키를 제공할 경로 설정
		
		response.addCookie(auto_cookie);
		
		//============로그아웃 처리 끝===============//
		
		
		return "redirect:/main/main";
	}
}
