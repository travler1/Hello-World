package kr.spring.mypage.advice.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.matching.service.MatchingService;
import kr.spring.matching.vo.AdviceVO;
import kr.spring.matching.vo.LetterVO;
import kr.spring.member.service.MemberService;
import kr.spring.member.vo.MemberVO;
import kr.spring.util.FileUtil;
import kr.spring.util.PageUtil;
import kr.spring.util.StringUtil;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MypageAdviceController {
   
   @ModelAttribute
   public AdviceVO init2() {
      return new AdviceVO();
   }
   
   @Autowired
   private MatchingService matchingService;
   @Autowired
   private MemberService memberService;
   
   @RequestMapping("/myPage/myAction")
   public String jobsMain(HttpServletRequest request, HttpSession session, Model model) {
      
      MemberVO user = (MemberVO)session.getAttribute("user");
      
      model.addAttribute("user", user);

       return "MyActionMain";   
   }
   
   //받은 첨삭 리스트
   @GetMapping("/myPage/myAdvice")
   public ModelAndView receivedAdvice(@RequestParam(value="pageNum",defaultValue="1") int currentPage, 
		   HttpSession session, String keyfield, String keyword) {
      
      MemberVO user = (MemberVO)session.getAttribute("user");
      int mem_num = user.getMem_num();
      
      Map<String,Object> map = new HashMap<String,Object>();
      map.put("keyfield", keyfield);
      map.put("keyword", keyword);
      map.put("receiver", mem_num);
      
      int count = matchingService.receivedAdviceCount(mem_num);
      log.debug("<<Advice Count >> : " + count);
      
      PageUtil page = new PageUtil(keyfield, keyword, currentPage, count, 20, 10, "MyAdviceAction");
      
      List<AdviceVO> list = null;
      if(count>0) {
         map.put("start", page.getStartRow());
         map.put("end", page.getEndRow());
         
         list = matchingService.selectReceivedAdvice(map);
         log.debug("<<AdviceVO list >> : " + list);
         
         if (list != null && !list.isEmpty()) {
             for (AdviceVO adviceVO : list) {
                 adviceVO.setAdvice_content(StringUtil.useNoHtml(adviceVO.getAdvice_content()));
                 adviceVO.setName(memberService.selectMember(adviceVO.getSender()).getName());
             }
         }
      }
      
      ModelAndView mav = new ModelAndView();
      mav.addObject("list",list);
      mav.addObject("count",count);
      mav.addObject("page",page.getPage());
      mav.setViewName("MyAdviceAction");
      
      return mav;
   }
   
   //특정 첨삭 출력
   @RequestMapping("/myPage/adviceDetail")
   public String adviceDetail(HttpSession session, Model model, HttpServletRequest request, int advice_num) {
      
      MemberVO user = (MemberVO)session.getAttribute("user");
      
      if(user==null) {
         model.addAttribute("message", "회원만 이용 가능합니다.");
         model.addAttribute("url", request.getContextPath() + "/main/login");

         return "common/resultAlert";
      }
      
      log.debug("<<adviceDetail user >> : " + user);
      
         AdviceVO adviceVO = matchingService.readAdvice(advice_num, session);
         log.debug("<<adviceDetail adviceVO >> : " + adviceVO);
         
         String sender = memberService.selectMember(adviceVO.getSender()).getName();
         String receiver = memberService.selectMember(adviceVO.getReceiver()).getName();
         String loginUser = user.getName();
         
         log.debug("<<AdviceDetail sender >> : " + sender);
         
         model.addAttribute("loginUser", loginUser);
         model.addAttribute("advice", adviceVO);
         model.addAttribute("sender", sender);
         model.addAttribute("receiver", receiver);
         model.addAttribute("advice_num", advice_num);
         
         return "AdviceDetail";   
   }
   
   //첨삭답장하기
      @GetMapping("myPage/answer_advice")
      public String answer_advice(HttpServletRequest request, HttpSession session, 
            String user_id, Model model) {

         MemberVO user = (MemberVO)session.getAttribute("user");
         log.debug("<<MemberVO (login)>> : " + user);
         log.debug("<<user_id mem_num >> : " + user_id);

         if(user==null) {//로그인 되지 않은 경우
            model.addAttribute("message", "회원만 이용가능합니다.");
            model.addAttribute("url", request.getContextPath() + "/main/login");

            return "matching/resultAlert";
         }else {//로그인
            model.addAttribute("login_user", user);
            model.addAttribute("receive_user", user_id);
         }

         return "matching/answer_advice";
      }
      
   //첨삭답장하기
   @PostMapping("myPage/answer_advice")
   public String answer_advice(HttpServletRequest request, HttpSession session, 
         Model model, @Valid AdviceVO adviceVO, BindingResult result, int sender, String receiverId) throws IllegalStateException, IOException {

      MemberVO user = (MemberVO)session.getAttribute("user");

      if(user==null) {
         model.addAttribute("message", "회원만 이용 가능합니다.");
         model.addAttribute("url", request.getContextPath() + "/main/login");
      }else {

         if(result.hasErrors()) {
            log.debug("<<result.getFieldValue(\"receiver\");>> : " +result.getFieldValue("receiver"));

            for (FieldError error : result.getFieldErrors()) {
               log.error("Field: " + error.getField() + ", Error: " + error.getDefaultMessage());
            }

            return answer_advice(request, session, receiverId, model);
         }

         log.debug("<<여기까지?>>");
         int receiverMem_num = matchingService.findMemnumById(receiverId);

         adviceVO.setSender(sender);
         adviceVO.setReceiver(receiverMem_num);
         adviceVO.setAdvice_ip(request.getRemoteAddr());
         adviceVO.setFilename(FileUtil.createFile(request, adviceVO.getUpload()));
         matchingService.insertAdvice(adviceVO);

         model.addAttribute("message", "메세지가 성공적으로 전송되었습니다.");
         model.addAttribute("url", request.getContextPath() + "myAdvice");
      }

      return "matching/resultAlert";
   }
   
   //받은 첨삭 삭제
   @GetMapping("/myPage/adviceReceiveDelete")
   public String adviceReceiveDelete(HttpSession session, Model model, HttpServletRequest request, int letter_num) {
      
      MemberVO user = (MemberVO)session.getAttribute("user");
      
      if(user==null) {
         model.addAttribute("message", "회원만 이용 가능합니다.");
         model.addAttribute("url", request.getContextPath() + "/main/login");

         return "common/resultAlert";
      }
      
      matchingService.deleteReceivedAdvice(letter_num);
      model.addAttribute("message", "성공적으로 삭제되었습니다.");
      model.addAttribute("url", request.getContextPath() + "/myPage/myadvice");

      
      return "common/resultAlert";
   }
   
   //보낸 첨삭 요청함
   @RequestMapping("/myPage/myAdviceSent")
   public ModelAndView receivedAdviceSent(@RequestParam(value="pageNum",defaultValue="1") int currentPage, HttpSession session,
                           String keyfield, String keyword) {
      
      MemberVO user = (MemberVO)session.getAttribute("user");
      int mem_num = user.getMem_num();
      
      Map<String,Object> map = new HashMap<String,Object>();
      map.put("keyfield", keyfield);
      map.put("keyword", keyword);
      map.put("sender", mem_num);
      
      int count = matchingService.sentAdviceCount(mem_num);
      log.debug("<<Advice Count >> : " + count);
      
      PageUtil page = new PageUtil(keyfield, keyword, currentPage, count, 20, 10, "MyAdviceAction");
      
      List<AdviceVO> list = null;
      if(count>0) {
         map.put("start", page.getStartRow());
         map.put("end", page.getEndRow());
         
         list = matchingService.selectSentAdvice(map);
         log.debug("<<LetterVO list >> : " + list);
         
         if (list != null && !list.isEmpty()) {
             for (AdviceVO adviceVO : list) {
                 adviceVO.setAdvice_content(StringUtil.useNoHtml(adviceVO.getAdvice_content()));
             }
         }
      }
      
      ModelAndView mav = new ModelAndView();
      mav.addObject("list",list);
      mav.addObject("count",count);
      mav.addObject("page",page.getPage());
      mav.setViewName("MyAdviceSentAction");
      
      return mav;
   }
   
   private static final String UPLOAD_PATH = "/upload";

   @RequestMapping("/myPage/download.do")
   public void fileDownload(int advice_num, HttpServletResponse response, HttpServletRequest request) throws IOException {
      
      AdviceVO adviceVO = matchingService.selectAdvice(advice_num);
      
      String file_path = request.getServletContext().getRealPath(UPLOAD_PATH);
      String fileName = adviceVO.getFilename();
      String downFile = file_path+"/"+fileName;      
      
      File f = new File(downFile);
      
      response.setContentType("application/download");
        response.setContentLength((int)f.length());
        response.setHeader("Content-disposition", "attachment;filename=\"" + fileName + "\"");
        // response 객체를 통해서 서버로부터 파일 다운로드
        OutputStream os = response.getOutputStream();
        // 파일 입력 객체 생성
        FileInputStream fis = new FileInputStream(f);
        FileCopyUtils.copy(fis, os);
        fis.close();
        os.close();
      
   }

}