package kr.spring.member.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.FileUtil;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemberController {
	@Autowired
	private MemberService memberService;


	/*==============================
	 * 자바빈(VO) 초기화
	 *==============================*/
	@ModelAttribute
	public MemberVO initCommand() {
		return new MemberVO();
	}

	/*==============================
	 * 회원가입 폼 호출
	 *==============================*/
	@GetMapping("/member/register")
	public String registerForm() {

		log.debug("<<일반 회원가입>>");

		return "memberRegister";
	}

	/*==============================
	 * 회원가입 처리
	 *==============================*/
	@PostMapping("/member/register")
	public String submit(@Valid MemberVO memberVO, BindingResult result, Model model, HttpServletRequest request) {

		log.debug("<<회원가입>> : "  + memberVO);

		//유효성 체크 결과 오류가 있으면 폼 호출
		if(result.hasErrors()) {
			return registerForm();
		}

		//회원가입
		memberService.insertMember(memberVO);

		model.addAttribute("accessTitle", "회원가입");
		model.addAttribute("accessMsg", "회원가입이 완료되었습니다.");
		model.addAttribute("accessUrl", request.getContextPath()+"/main/main");

		return "common/resultView";
	}
	
	/*=========================
	 *	    프로필 사진 출력
	 *=========================*/
	//프로필 사진 출력(로그인 전용)
	@RequestMapping("/member/photoView")
	public String getProfile(HttpSession session, HttpServletRequest request, Model model) {
		
		MemberVO user = (MemberVO)session.getAttribute("user");
		log.debug("<<프로필사진 읽기>> : " + user);
		if(user==null) {//로그인이 되지 않은 경우
			getBasicProfileImage(request,model);
		}else {//로그인 된 경우
			MemberVO memberVO = memberService.selectMember(user.getMem_num());
			viewProfile(memberVO, request, model);
		}
		return "imageView"; //타일스 설정하면 안됨. class파일이 불러와져야함.
							//빈의 이름이 imageView인 ImageView 객체 호출
	}
	
	//프로필 사진(회원번호 지정)
	@RequestMapping("/member/viewProfile")
	public String getProfileByMem_num(@RequestParam int mem_num, HttpServletRequest request, Model model) {
		
		MemberVO memberVO = memberService.selectMember(mem_num);
		
		viewProfile(memberVO,request,model);
		
		return "imageView";
	}
	//프로필 사진 처리를 위한 공통 코드
	public void viewProfile(MemberVO memberVO, HttpServletRequest request, Model model) {
		if(memberVO==null || memberVO.getPhoto_name()==null) {
			//업로드한 프로필 사진 정보가 없어서 기본 이미지 표시
			getBasicProfileImage(request,model);
		}else {//업로드한 이미지 읽기
			model.addAttribute("filename",memberVO.getPhoto_name());
		}
	}
	
	//기본 이미지 읽기
	public void getBasicProfileImage(HttpServletRequest request, Model model) {
		model.addAttribute("basicProfile", "yes");
		model.addAttribute("filename", "basicprofile.png");
	}
	
	
}
