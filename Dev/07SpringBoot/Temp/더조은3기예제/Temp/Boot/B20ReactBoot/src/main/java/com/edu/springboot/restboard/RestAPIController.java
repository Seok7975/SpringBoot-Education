package com.edu.springboot.restboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

//메서드에서 모든 반환값은 웹브라우저에 즉시 출력된다. 
@RestController
public class RestAPIController {
	
	//인터페이스로 정의된 DAO빈 자동주입 
	@Autowired
	IBoardService dao;
	
	/* board 테이블의 레코드를 각 페이지별로 구분해서 배열 형태로 출력한다.
	페이지 번호가 없는 경우 1페이지의 내용을 출력하게된다. */
	@GetMapping("/restBoardList.do")
	public List<BoardDTO> restBoardList(ParameterDTO parameterDTO){
		//JSON배열로 출력하므로 List 컬렉션을 반환한다. 
		
		//한 페이지에 10개씩 출력
		int pageSize = 10;
		//페이지 번호 가져오기
		int pageNum = parameterDTO.getPageNum()==null ? 1 :
			Integer.parseInt(parameterDTO.getPageNum());
		//게시물의 구간 계산 
		int start = (pageNum-1) * pageSize + 1;
		int end = pageNum * pageSize;
		//DTO에 저장한 후 Mapper를 호출한다
		parameterDTO.setStart(start);
		parameterDTO.setEnd(end);
		/* Mapper의 <select> 엘리먼트는 쿼리 실행결과를 List로 반환하게 
		정의되어 있다 */
		List<BoardDTO> boardList = dao.list(parameterDTO);
		return boardList;
	}
	
	@GetMapping("/restBoardSearch.do")
	public List<BoardDTO> restBoardSearch(HttpServletRequest req, 
			ParameterDTO parameterDTO){
		//searchField는 parameterDTO가 받음
		//searchWord는 별도로 저장해야함
		if(req.getParameter("searchWord")!=null) {		
			String[] sTxtArray = req.getParameter("searchWord")
					.split(" ");
			parameterDTO.getSearchWord().clear();
			for(String str : sTxtArray) {
				System.out.println(str);
				parameterDTO.getSearchWord().add(str);
			}
		}		
		List<BoardDTO> searchList = dao.search(parameterDTO);
		return searchList;
	}

	@GetMapping("/restBoardView.do")
	public BoardDTO restBoardView(ParameterDTO parameterDTO){
		BoardDTO boardDTO = dao.view(parameterDTO);
		return boardDTO;
	}

	@PostMapping("/restBoardWrite.do")
	public Map<String, Integer> restBoardWrite(BoardDTO boardDTO){
		System.out.println("요청들어옴"+ boardDTO);
		int result = dao.write(boardDTO);
		Map<String, Integer> map = new HashMap<>();
		map.put("result", result);
		return map;
	}
}
