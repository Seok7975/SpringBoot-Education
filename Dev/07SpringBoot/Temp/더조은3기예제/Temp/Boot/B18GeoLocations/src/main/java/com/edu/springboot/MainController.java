package com.edu.springboot;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.springboot.mybatis.ISearchRadius;
import com.edu.springboot.mybatis.MyFacilityDTO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController {

	@Autowired
	ISearchRadius dao;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
 
	//구글맵 API 키를 상수로 설정 
	//private static final String apiKey = "AIzaSyCktZFiCeZuQSXfdamncAGNaZQat0RIWms";
	private static final String apiKey = "AIzaSyCVoEX4YifBDjG7bR3jLr_nTtinXD7EqcE";
	
	//내위치값 알아내기
	@GetMapping("/01GeoLocation.do")
	public String geoFunc1(Model model) {
		model.addAttribute("apiKey", apiKey);
		return "01GeoLocation";
	}

	//구글맵 연동
	@GetMapping("/02GoogleMap.do")
	public String geoFunc2(Model model) {
		model.addAttribute("apiKey", apiKey);
		return "02GoogleMap";
	}
	
	//구글맵에 내위치 출력하기
	@GetMapping("/03MyLocation.do")
	public String geoFunc3(Model model) {
		model.addAttribute("apiKey", apiKey);
		return "03MyLocation";
	}		
		 
	//내 위치기반 시설물 반경 검색
	@GetMapping("/04SearchRadius.do")
	public String geoFunc4(Model model, HttpServletRequest req) {
		//API키를 모델 객체에 저장 
		model.addAttribute("apiKey", apiKey);
		
		//첫 진입시에는 파라미터가 없는 상태이므로 모두 0으로 처리한다.
		//거리지정(1Km, 5Km 등)
		int distance = (req.getParameter("distance")==null) ? 
				0 : Integer.parseInt(req.getParameter("distance"));
		//위도,경도(내위치) 
		double latTxt = (req.getParameter("latTxt")==null) ? 
				0 : Double.parseDouble(req.getParameter("latTxt"));
		double lngTxt = (req.getParameter("lngTxt")==null) ?  
				0 : Double.parseDouble(req.getParameter("lngTxt"));
		
		//한번에 200개의 시설물 정보를 지도에 표시 
		int numberPerPage = 200; 
		//파라미터를 통해 조건에 맞는 시설물의 갯수 확인 
		int resultCount = dao.searchCount(distance, latTxt, lngTxt);
		//뷰에서 검색결과 출력을 위해 모델객체 저장 
		model.addAttribute("resultCount", " / 검색결과:"+resultCount+"건");
		//카운트수를 이용해서 전체 페이지수를 계산(<select>태그에서 사용) 
		model.addAttribute("selectNum", 
				Math.ceil(resultCount/numberPerPage));
		//파라미터로 전달된 페이지번호 처리 
		int pageNum = (req.getParameter("pageNum")==null) ? 
				1 : Integer.parseInt(req.getParameter("pageNum"));
		//한 페이지당 출력할 시설물의 구간을 계산 
		int start = ((pageNum - 1) * numberPerPage) + 1; 
		int end = pageNum * numberPerPage;
		System.out.println(distance +" "+ latTxt +" "+ lngTxt 
				+" "+ start +" "+ end);	
		/* 첫 진입시에는 모든 파라미터가 없는 상태이므로 select를 실행하지
		않는다. 현재위치와 거리의 조건이 있는 경우에만 레코드를 인출한다. */
		ArrayList<MyFacilityDTO> searchLists = null;
		if(distance!=0) {
			searchLists = 
				dao.searchRadius(distance, latTxt, lngTxt, start, end);
		}
		//인출한 레코드를 모델객체에 저장 
		model.addAttribute("searchLists", searchLists);
		
		return "04SearchRadius";
	}	
	
}
 





