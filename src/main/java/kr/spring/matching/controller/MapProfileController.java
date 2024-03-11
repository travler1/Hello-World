package kr.spring.matching.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.FileUtil;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MapProfileController {
	
	@Autowired
	MemberService memberService;
	
	@RequestMapping("/matching/photoView")
	public String getProfile(HttpSession session,HttpServletRequest request,
			                 @RequestParam(value="userNum") int user_num, Model model) {
		
		MemberVO member = memberService.selectMember(user_num);

		log.debug("<<프로필 사진 읽기>> : " + member);
		if(member.getPhoto_name()==null) {//로그인이 되지 않은 경우
;			getBasicProfileImage(request, model);
		}else {//로그인 된 경우
			viewProfile(member, request, model);
		}
		//빈의 이름이 imageView인 ImageView 객체 호출
		return "imageView";
	}
	
	//프로필 사진 출력(회원번호 지정)
	@RequestMapping("/matching/viewProfile")
	public String getProfileByMem_num(@RequestParam(value="userNum") int user_num,
			                          HttpServletRequest request,
			                          Model model) {
		MemberVO memberVO = memberService.selectMember(user_num);
		log.debug("<<Member>> : " + memberVO);
		log.debug("<<Member의 프로필 사진 >> : " + memberVO.getPhoto_name());
		
		viewProfile(memberVO,request,model);
		
		return "imageView";
	}
	//프로필 사진 처리를 위한 공통 코드
	public void viewProfile(MemberVO memberVO, HttpServletRequest request,
			                                                  Model model) {
		if(memberVO==null || memberVO.getPhoto_name()==null) {
			//업로드한 프로필 사진 정보가 없어서 기본 이미지 표시
			getBasicProfileImage(request, model);
		}else {//업로드한 이미지 읽기
			model.addAttribute("filename", memberVO.getPhoto_name());
		}
	}
	//기본 이미지 읽기
	public void getBasicProfileImage(HttpServletRequest request, Model model) {
		model.addAttribute("filename", "basicprofile.png");
		model.addAttribute("basicProfile","yes");
	}
	
}
