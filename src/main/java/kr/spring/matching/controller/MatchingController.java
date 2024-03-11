package kr.spring.matching.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.spring.matching.service.MatchingService;
import kr.spring.matching.vo.EmpVO;
import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.FileUtil;
import kr.spring.util.PageUtil;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MatchingController {
	
	@Autowired
	MemberService memberService;
	@Autowired
	MatchingService matchingService;
	
	
	@GetMapping("/matching/main")
	public String matchingMain(@RequestParam(defaultValue="1") int currentPage, Model model,
			@RequestParam(value="rowCount",defaultValue="100") int rowCount,
			HttpSession session) throws JsonProcessingException {
		
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map<String,Double>> mapDataList = new ArrayList<Map<String, Double>>();
		List<EmpVO> empList;
		List<MemberVO> memberList;
		
		MemberVO member = (MemberVO)session.getAttribute("user");
		model.addAttribute("user",member);
		
		//지도에 표시할 멤버리스트를 만들기 위한 페이지 처리
		int count = matchingService.selectEmpCount();
		model.addAttribute("count", count);
		PageUtil page = new PageUtil(currentPage,count,rowCount);
		map.put("start", page.getStartRow());
		map.put("end", page.getEndRow());
		
		//지도에 표시할 리스트 생성
		if(count>0) {
			empList = matchingService.listEmp(map);
			log.debug("<<listEmp >> : " + map);
			ObjectMapper objectMapper = new ObjectMapper();
			String empListJson = objectMapper.writeValueAsString(empList);
			log.debug("<<empList (size)>> : " + empList.size());
			model.addAttribute("empListJson", empListJson);
			//멤버 프로필리스트 생성
			memberList = matchingService.selectMemberList();
			model.addAttribute("memberList", memberList);
			log.debug("<<count>> : " + count);
			log.debug("<<memberList>> : " +memberList);
			
		}else {
			empList = Collections.emptyList();
		}
		log.debug("<<empList>> : " + empList);
		
		//주소 좌표 정보 JSON 처리
	  for(EmpVO empVO : empList) { 
		  if (empVO.getLocation_api_lat() != null && empVO.getLocation_api_lng() != null) {
			  
		  Map<String,Double> mapData = new LinkedHashMap<String,Double>();
		  mapData.put("lat",Double.valueOf(empVO.getLocation_api_lat())); 
		  mapData.put("lng",Double.valueOf(empVO.getLocation_api_lng()));
		  mapData.put("user_num",Double.valueOf(empVO.getMem_num()));
		 
		  mapDataList.add(mapData); 
		  }
	  }

		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxMapData = "{\"positions\": "; 
		ajaxMapData += mapper.writeValueAsString(mapDataList);
		ajaxMapData += "}";
		model.addAttribute("ajaxMapData", ajaxMapData);
		
		log.debug("<<mapDataList >> : " + ajaxMapData);
		
		return "matchingMain";
	}
	
	@GetMapping("/matching/emp_register")
	public String main(
			HttpSession session, Model model, HttpServletRequest request) throws JsonProcessingException {

		MemberVO user = (MemberVO)session.getAttribute("user");

		if(user==null) {
			//View에 표시할 메시지
			model.addAttribute("message", "수강생 회원만 이용가능합니다.");
			model.addAttribute("url", request.getContextPath()+ "/main/login");

			return "matching/resultAlert";
		}else {
			model.addAttribute(user);
		}

		return "matching/emp_register";
	}
	
	@PostMapping("/matching/emp_register")
	public String registerEmp(@Valid EmpVO empVO, BindingResult result, Model model, HttpSession session, 
			HttpServletRequest request) throws IllegalStateException, IOException {

		log.debug("<< 합격자 신청 폼 >> : " + empVO);

		//회원번호 셋팅
		MemberVO memberVO = (MemberVO)session.getAttribute("user");
		empVO.setMem_num(memberVO.getMem_num());
		empVO.setFilename(FileUtil.createFile(request, empVO.getUpload()));
		
		//DB에 empVO 등록
		matchingService.insertEmp(empVO);

		//View에 표시할 메시지
		model.addAttribute("message", "글쓰기가 완료되었습니다.");
		model.addAttribute("url", request.getContextPath()+ "/matching/main");

		return "matching/resultAlert";

	}
	
	@GetMapping("/matching/my_emp_register")
	public String main2(HttpSession session, Model model, HttpServletRequest request) {

		MemberVO user = (MemberVO)session.getAttribute("user");

		if(user==null) {
			//View에 표시할 메시지
			model.addAttribute("message", "수강생 회원만 이용가능합니다.");
			model.addAttribute("url", request.getContextPath()+ "/main/login");

			return "matching/resultAlert";
		}else if(user.getAuth()!=2) {
			//View에 표시할 메시지
			model.addAttribute("message", "현직자만 이용가능합니다.");
			model.addAttribute("url", request.getContextPath()+ "/main/login");

			return "matching/resultAlert";
		}
		else {
			EmpVO empVO = matchingService.selectEmp(user.getMem_num());
			model.addAttribute("empVO",empVO);
			model.addAttribute("user");
		}

		return "matching/my_emp_register";
	}
	
	@GetMapping("/matching/see_emp_register")
	public String main3(int user_id,HttpSession session, Model model, HttpServletRequest request) {

		MemberVO user = (MemberVO)session.getAttribute("user");

		if(user==null) {
			//View에 표시할 메시지
			model.addAttribute("message", "수강생 회원만 이용가능합니다.");
			model.addAttribute("url", request.getContextPath()+ "/main/login");

			return "matching/resultAlert";
		}else {
			EmpVO empVO = matchingService.selectEmp(user_id);
			log.debug("<<empVO>> : " + empVO);
			model.addAttribute("empVO",empVO);
			model.addAttribute("user");
		}

		return "matching/see_emp_register";
	}
	
}
