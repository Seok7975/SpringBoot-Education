package com.edu.springboot.se2;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class PostController {

	@PostMapping("/singleImageUpload.do")
	@ResponseBody
	public void singleImageUpload(MultipartFile file,
		HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("호출됨??");
		
		try{
            if(file.isEmpty()) {
                System.out.println("이미지 미등록");
            } 
            else {            	
                String filePath = ResourceUtils.getFile("classpath:static/uploads/").toPath().toString();
                File saveDir = new File(filePath);
                if(!saveDir.exists()){
                    saveDir.mkdir();
                }
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
                String today = formatter.format(new java.util.Date());
                String rfileName = file.getOriginalFilename();
                String fileExt = file.getOriginalFilename().split("\\.")[1];
                String pFileName = file.getOriginalFilename().split("\\.")[0] + UUID.randomUUID() + "-" + today + "."+ fileExt;
                File saveFile = new File(saveDir, pFileName);
                try {
                    file.transferTo(saveFile);
                }catch(Exception e) {
                    e.printStackTrace();
                }
                String sFileInfo = "";
                sFileInfo += "&bNewLine=true";
                // img 태그의 title 속성을 원본파일명으로 적용시켜주기 위함
                sFileInfo += "&sFileName=" + rfileName;
                sFileInfo += "&sFileURL=" + "/uploads/" +pFileName;
                PrintWriter print = response.getWriter();
                print.print(sFileInfo);
                print.flush();
                print.close();
            }
        } 
		catch (Exception e) {
            e.printStackTrace();
        }	    
	}
	
}
