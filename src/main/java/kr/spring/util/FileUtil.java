package kr.spring.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class FileUtil {
	//업로드 상대 경로
	private static final String UPLOAD_PATH = "/upload";
	
	//파일생성
	public static String createFile(HttpServletRequest request,MultipartFile file) throws IllegalStateException, IOException {
		//절대경로 구하기
		String absolutePath = request.getServletContext().getRealPath(UPLOAD_PATH);
		//파일명 생성
		
		String filename = null;
		if(!file.isEmpty()) {
			filename = UUID.randomUUID()+"_"+file.getOriginalFilename();
			//원하는 경로에 파일 저장
			file.transferTo(new File(absolutePath+"/"+filename));
		}
				
		return filename;
	}
	//파일삭제
	public static void removeFile(HttpServletRequest request, String filename) {
		if(filename!=null) {
			//업로드 절대 경로
			String absolutePath = request.getServletContext().getRealPath(filename);
			File file = new File(absolutePath+"/"+filename);
			if(file.exists()) file.delete();
		}
		
	}
	
	public static byte[] getBytes(String path) {
		FileInputStream fis = null;
		byte[] readbyte = null;
		try {
			fis = new FileInputStream(path);
			readbyte = new byte[fis.available()];
			fis.read(readbyte);
		}catch(Exception e) {
			log.error(e.toString());
		}finally {
			if(fis!=null) try {fis.close();}catch(IOException e) {}
		}
		return readbyte;
	}
	//다중 파일 생성
		public static String createMultiFile(HttpServletRequest request, MultipartFile[] file, int count) throws IllegalStateException, IOException {
			
			//절대 경로 구하기
			String absolutePath = request.getServletContext().getRealPath(UPLOAD_PATH);
			//파일명 생성
			String filename =  null;
				if(!file[count].isEmpty()) {
					filename = UUID.randomUUID()+"_"+file[count].getOriginalFilename();
					//원하는 경로에 파일 저장
					file[count].transferTo(new File(absolutePath+"/"+filename));
					
			}
			
			return filename;
		}
		//다중 파일 생성
		
}
