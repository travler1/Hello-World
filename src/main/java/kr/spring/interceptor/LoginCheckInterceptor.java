package kr.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
			Object Handler) throws Exception {
		
		log.debug("<<LoginCheckInterceptor 진입>>");
		
		HttpSession session = request.getSession(); //여기선 왜 파라미터로 session을 받지 않았을까
		//로그인 여부 검사
		if(session.getAttribute("user")==null) {
			//로그인이 되지 않은 상태
			response.sendRedirect(request.getContextPath() + "/member/login"); //redirect로도 되고, forward로도 됨.
			return false;//요청한 페이지 호출되지 않음
		}
		
		return true;//요청한 페이지 호출
		//true일 경우 우리가 요청한 페이지 호출, false일 경우 우리가 요청한 페이지가 아닌 다른 페이지 호출(로그인페이지)
	}
}
