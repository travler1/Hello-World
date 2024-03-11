package kr.spring.main.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.spring.member.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CommonController {
	@RequestMapping("/common/imageUploader")
	@ResponseBody
	public Map<String,Object> uploadImage(MultipartFile upload,HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception{
		//업로드할 폴더 경로
		String realFolder = session.getServletContext().getRealPath("/image_upload");
		//업로드할 파일 이름
		String org_filename = upload.getOriginalFilename();
		String str_filename = System.currentTimeMillis() + "_" + org_filename;
		
		log.debug("<<원본 파일명>> : "+org_filename);
		log.debug("<<저장할 파일명>> : "+str_filename);
		
		String sub_path;//중간경로
		MemberVO user = (MemberVO)session.getAttribute("user");
		if(user == null) {
			sub_path = "general";
		}else {
			sub_path = String.valueOf(user.getMem_num());
		}
		String file_path = realFolder + "/" + sub_path + "/" + str_filename;
		log.debug("<<파일 경로>> : " + file_path);
		File f = new File(file_path);
		if(!f.exists()) {
			//상위 경로를 생성해야 하기때문에 mkdirs()사용
			f.mkdirs();
		}
		//희망하는 경로에 파일 저장
		upload.transferTo(f);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("uploaded", true);
		map.put("url", request.getContextPath()+"/image_upload/"+sub_path+"/"+str_filename);
		
		
		
		
		return map;
	}
}
