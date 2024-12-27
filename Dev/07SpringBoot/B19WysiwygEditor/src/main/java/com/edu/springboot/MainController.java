package com.edu.springboot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.edu.springboot.se2.ParamDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class MainController {
	@RequestMapping("/")
	public String home() { 
		return "home";
	}
		
	//네이버 스마트 에디터 : 버전 2.8 적용
	@GetMapping("/naverSmartEditor.do")
	public String naverSmartEditor() {
		return "naverSmartEditor";
	}	
	@PostMapping("/naverSmartEditor.do")
	public String naverSmartEditor(HttpServletRequest req, Model model) {
		String subject = req.getParameter("subject");
		String contents = req.getParameter("contents");
		
		System.out.println(subject);
		System.out.println(contents);
		
		model.addAttribute("submit", "폼값전송됨");
		return "naverSmartEditor";
	}
	@PostMapping("/file_uploader_html5.do")
	@ResponseBody
	public String fileUpload(
			@RequestParam("ofile") MultipartFile ofile,
			HttpServletRequest request, 
			HttpServletResponse response) 
					throws IOException {		
		String result = "";		
		if (ofile.isEmpty()) {
            return "파일이 비어 있습니다.";
        }

        // 파일의 원본 이름을 가져옴
        String fileName = ofile.getOriginalFilename();
        // 저장 디렉토리의 물리적경로
        String uploadDir = ResourceUtils
			.getFile("classpath:static/uploads/").toPath()
			.toString();
        //System.out.println(uploadDir);

        // 파일 저장
        try {
            byte[] bytes = ofile.getBytes();
            System.out.println(Paths.get(uploadDir + 
            		File.separator + fileName));
            Files.write(Paths.get(uploadDir + 
            		File.separator + fileName), bytes);
        } 
        catch (IOException e) {
            e.printStackTrace();
            return "파일 처리 중 오류 발생";
        }
		
        //콜백 데이터
        result = "&bNewLine=true"
        		+"&sFileName="+ fileName
				+"&sFileURL=/uploads/"+ fileName;        
		return result;
	}
	
	

	//CK Editor
	@GetMapping("/CKEditor.do")
	public String CKEditor() {
		return "CKEditor";
	}	
	@PostMapping("/CKEditor.do")
	public String CKEditor(HttpServletRequest req, Model model) {
		String subject = req.getParameter("subject");
		String contents = req.getParameter("contents");

		System.out.println("subject="+ subject);
		System.out.println("contents="+ contents);
		
		if(contents==null || contents.equals("")) {
			System.out.println("내용을 입력해주세요");
			model.addAttribute("submit", "내용 검증 에러");
		}
		else {
			model.addAttribute("submit", "폼값 전송됨");
		}		
		return "CKEditor";
	}

	
	
	//Summer note Editor
	@GetMapping("/summerNote.do")
	public String summerNote() {
		return "summerNote";
	}	
	@PostMapping("/summerNote.do")
	public String summerNote(HttpServletRequest req, Model model) {
		String subject = req.getParameter("subject");
		String contents = req.getParameter("contents");

		System.out.println("subject="+ subject);
		System.out.println("contents="+ contents);
		
		if(contents==null || contents.equals("")) {
			System.out.println("내용을 입력해주세요");
			model.addAttribute("submit", "내용 검증 에러");
		}
		else {
			model.addAttribute("submit", "폼값 전송됨");
		}
		return "summerNote";
	}
	
	
	
	//스마트에디터 2.10 버전
	@GetMapping("/naverSmartEditor-2.10.do")
	public String nse1() {
		return "naverSmartEditor210";
	}	
	@PostMapping("/naverSmartEditor-2.10.do")
	public String nse1(Model model, ParamDTO paramDTO) {
		System.out.println(paramDTO);
		return "naverSmartEditor210";
	}
	
	//스마트에디터 2.8 버전
	@GetMapping("/naverSmartEditor-2.8.do")
	public String nse2() {
		return "naverSmartEditor28";
	}	
	@PostMapping("/naverSmartEditor-2.8.do")
	public String nse2(Model model, ParamDTO paramDTO) {
		System.out.println(paramDTO);
		return "naverSmartEditor28";
	}
}






