package com.edu.springboot;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import utils.MyFunctions;

@Controller
public class MainController {

	@RequestMapping("/notview")
	@ResponseBody
	public String main() {
		return "View 없이 컨트롤러에서 즉시 출력";
	}
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	//싱글 파일 업로드 매핑 
	@GetMapping("/fileUpload.do")
	public String fileUpload(){
		return "fileUpload";
	}
	//싱글파일 업로드 처리 
	@PostMapping("/uploadProcess.do")
	public String uploadProcess(HttpServletRequest req, Model model){
		try {
			//업로드 디렉토리의 물리적경로 얻어오기 
			String uploadDir = ResourceUtils
				.getFile("classpath:static/uploads/").toPath().toString();
			System.out.println("물리적경로:"+uploadDir);
			
			//전송된 첨부파일을 Part객체를 통해 얻어온다. 
			Part part = req.getPart("ofile");	
			//파일명 확인을 위해 헤더값을 얻어온다. 
			String partHeader = part.getHeader("content-disposition");
		    System.out.println("partHeader="+ partHeader);
		    //헤더값에서 파일명 추출을 위해 문자열을 split()한다. 
		    String[] phArr = partHeader.split("filename=");
		    //따옴표를 제거한 후 원본파일명을 추출한다. 
		    String originalFileName = phArr[1].trim().replace("\"", "");
		    //전송된 파일이 있다면 서버에 저장한다. 
		    if (!originalFileName.isEmpty()) {				
				part.write(uploadDir+ File.separator +originalFileName);
			}
		    
		    //서버에 저장된 파일명을 중복되지 않는 이름으로 변경한다. 
		    String savedFileName = 
		    	MyFunctions.renameFile(uploadDir, originalFileName);
		    
		    //JDBC연동을 하지 않으므로 Model객체에 정보를 저장한다. 
		    model.addAttribute("originalFileName", originalFileName);			
		    model.addAttribute("savedFileName", savedFileName);		
		    //파일 외 나머지 폼값도 받아서 저장한다. 
		    model.addAttribute("title", req.getParameter("title"));			
		    model.addAttribute("cate", req.getParameterValues("cate"));			
		}
		catch (Exception e) {
			System.out.println("업로드 실패");
		}
		
		//root로 이동하기
		return "fileUploadOk";
	}


	//멀티 파일 업로드 폼 매핑
	@GetMapping("/multiFileUpload.do")
	public String multiFileUpload(){
		return "multiFileUpload";
	}
	//파일 업로드 처리
	@PostMapping("/multiUploadProcess.do")
	public String multiUploadProcess(HttpServletRequest req, Model model){
		try {
			//물리적 경로 얻어오기 
			String uploadDir = ResourceUtils
				.getFile("classpath:static/uploads/").toPath().toString();
			System.out.println("물리적경로:"+uploadDir);
			
			/* 파일명 저장을 위한 Map 생성. Key는 원본파일명, value는 서버에 저장된
			파일명을 저장한다. */
			Map<String, String> saveFileMaps = new HashMap<>();
			
			//2개 이상의 파일이므로 getParts() 메서드를 통해 폼값을 받는다. 
			Collection<Part> parts = req.getParts();
			//폼값의 갯수만큼 반복 
			for(Part part : parts) {
				/* 폼값 중 파일인 경우에만 업로드 처리를 위해 continue를 걸어준다.
				즉 파일이 아니라면 for문의 처음으로 돌아간다. */
				if(!part.getName().equals("ofile"))
					continue;	
				
				//파일명 추출을 위해 헤더값을 얻어온다.
		        String partHeader = part.getHeader("content-disposition");
		        System.out.println("partHeader="+ partHeader);
		        //파일명을 추출한 후 따옴표를 제거한다. 
		        String[] phArr = partHeader.split("filename=");
		        String originalFileName = phArr[1].trim().replace("\"", "");
				//파일을 원본파일명으로 저장한다. 
				if (!originalFileName.isEmpty()) {				
					part.write(uploadDir+ File.separator +originalFileName);
				}
				//저장된 파일을 UUID로 생성한 새로운 파일명으로 저장한다. 
				String savedFileName = MyFunctions.renameFile(uploadDir, 
						originalFileName);
				
				/* Map컬렉션에 원본파일명과 저장된파일명을 key와 value로 
				저장한다.*/
				saveFileMaps.put(originalFileName, savedFileName);
			}
			//View로 전달하기 위해 Model객체에 저장한다. 
		    model.addAttribute("saveFileMaps", saveFileMaps);		
		    model.addAttribute("title", req.getParameter("title"));			
		    model.addAttribute("cate", req.getParameterValues("cate"));			
		}
		catch (Exception e) {
			System.out.println("업로드 실패");
		}
		
		//View로 포워드 
		return "multiFileUploadOk";
	}
}
