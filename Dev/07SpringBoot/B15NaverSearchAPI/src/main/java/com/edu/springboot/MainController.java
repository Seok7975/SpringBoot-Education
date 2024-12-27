package com.edu.springboot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
	
	@RequestMapping("/NaverSearchMain.do")
	public String NaverSearchMain() {
		return "SearchView";
	}
	
	/*
	메서드에서 반환되는 값은 화면에 즉시 출력된다. 
	view로 포워드 되지 않는다. 
	 */
	@ResponseBody
	@GetMapping("/NaverSearchRequest.do")
	public String NaverSearchRequest(HttpServletRequest req, 
			HttpServletResponse resp) {		
		// 1.네이버검색 API 값 
        String clientId = "Ph_2Da_aeob_OLYiChcG"; 
        String clientSecret = "zydssfN2o9"; 

        // 2. 검색 조건 설정
        int startNum = 0;//검색을 시작할 위치(페이지번호)    
        String text = null;//사용자가 입력한 검색어 
        try {
        	//파라미터로 받은 후 정수변환
            startNum = Integer.parseInt(req.getParameter("startNum"));
            //검색어의 경우 UTF-8로 인코딩 처리 
            String searchText = req.getParameter("keyword");
            text = URLEncoder.encode(searchText, "UTF-8");
        } 
        catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패", e);
        }

        // 3.네이버 검색 API의 요청 URL조립(클라이언트가 입력한 내용으로
        //    파라미터를 설정한다)
        String apiURL = "https://openapi.naver.com/v1/search/blog?"
        		+ "query="+text+"&display=10&start="+startNum;
        //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query=" + text;  // xml 결과

        // 4. API 호출
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL, requestHeaders);

        // 5. 결과 출력
        System.out.println(responseBody);  // 콘솔에 출력
		return responseBody;
	}
	
	private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}
