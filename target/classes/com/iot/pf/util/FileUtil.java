package com.iot.pf.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.iot.pf.dto.Attachment;

@Component
public class FileUtil {
	
	@Value("${file.upload.directory}")
	private String fileUploadDirectory;
	
//	private String fileUploadDirectory = "c:/tmp/upload";
//	private String fileUploadDirectory = "/home/ubuntu/app/upload/pf";
	
	private static Logger logger = Logger.getLogger(FileUtil.class);
	
	/**
	 * 첨부파일을 서버 물리 저장소에 복사
	 * @param files
	 */
	public void copyToFoler(MultipartFile f, String fakeName) {
		//inputStream을 만들 필요는 없다 getByte 찍으면 나오기 때문
		File target = new File(fileUploadDirectory);
		if(!target.exists())
			target.mkdirs();
		
		
		target = new File(target, fakeName);
		
		//이렇게 썼지만 spring이 있으니 이거 말고 밑에 방식을 쓴다.
		//FileOutputStream fos = new FileOutputStream(target); 
		
		//물리저장소에 파일 쓰기
		try {
			FileCopyUtils.copy(f.getBytes(), target);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public byte[] getDownloadFileBytes(Attachment attach, HttpServletResponse rep) {
		String uploadDir = fileUploadDirectory;
//		String uploadDir = "/home/ubuntu/app/upload/pf";
		File file = new File(uploadDir + "/" + attach.getFakeName());
		
		//한글 파일명 인코딩
		String endcodingName;
		byte[] b =null;
		try {
			endcodingName = java.net.URLEncoder.encode(attach.getFilename(), "UTF-8");
			
			rep.setHeader("Content-Disposition", "attachment; filename=\"" + endcodingName + "\"");
			rep.setContentType(attach.getContentType());
			rep.setHeader("pragma", "no-cache");
			rep.setHeader("Cache-Control", "no-cache");
			rep.setContentLength((int) attach.getFileSize());
			
			b=FileUtils.readFileToByteArray(file);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
		
	}
}
