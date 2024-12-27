package com.edu.springboot;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailSending {

	private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;
    
    public void myEmailSender(InfoDTO infoDTO) {
    	
    	try {
	        MimeMessage m = mailSender.createMimeMessage();
	        MimeMessageHelper h = new MimeMessageHelper(m,"UTF-8");
	        
	        h.setFrom(from);
	        h.setTo(infoDTO.getTo());	        
	        h.setSubject(infoDTO.getSubject());
	        if(infoDTO.getFormat().equals("text")) {
	        	h.setText(infoDTO.getContent());
	        }
	        else {
	        	String emailTpl = readHTMLFile();
	        	String contents = infoDTO.getContent().replace("\r\n","<br/>");
	        	emailTpl = emailTpl.replace("__CONTENT__", contents);
	        	h.setText(emailTpl, true);
	        }
	        
	        mailSender.send(m);
	        System.out.println("메일 전송 완료..!!");
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public String readHTMLFile() {
    	StringBuffer htmlContents = new StringBuffer();
    	try {
    		String uploadDir = ResourceUtils
					.getFile("classpath:static/").toPath().toString();				
	        String templatePath = uploadDir+"/MailTemplate.html";
	        BufferedReader br = new BufferedReader(new FileReader(templatePath));
	        String oneLine;
	        while ((oneLine = br.readLine()) != null) {
	        	htmlContents.append(oneLine + "\n");
	        }
	        br.close();
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
    	return htmlContents.toString();
    }
}
