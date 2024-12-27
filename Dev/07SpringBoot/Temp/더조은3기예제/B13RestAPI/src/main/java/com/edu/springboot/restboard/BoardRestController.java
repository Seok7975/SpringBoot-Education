package com.edu.springboot.restboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

/*
해당 컨트롤러에서 반환되는 모든 문자열은 웹브라우저에 즉시 출력된다. 
즉 View로 포워드 되지 않는다. 
 */
@RestController
public class BoardRestController {
	
	@Autowired
	IBoardService dao;
	
	/*
	board테이블의 레코드를 각 페이지별로 구분해서 배열 형태로 출력한다.
	페이지 번호가 없는 경우 1페이지의 내용을 출력하게된다. 
	 */
	@GetMapping("/restBoardList.do")
	public List<BoardDTO> restBoardList(ParameterDTO parameterDTO){
		//한 페이지에 10개씩 출력
		int pageSize = 10;
		//파라미터로 전달된 페이지번호 가져오기 
		int pageNum = parameterDTO.getPageNum()==null ? 1 :
			Integer.parseInt(parameterDTO.getPageNum());
		//페이지번호를 통해 게시물의 구간을 계산한다. 
		int start = (pageNum-1) * pageSize + 1;
		int end = pageNum * pageSize;
		//계산된 값은 DTO에 저장한다. 
		parameterDTO.setStart(start);
		parameterDTO.setEnd(end);
		//Mapper를 호출하여 레코드를 List로 반환받는다. 
		List<BoardDTO> boardList = dao.list(parameterDTO);
		//결과를 화면에 출력한다. 
		return boardList;
	}
	
	//검색하기 
	@GetMapping("/restBoardSearch.do")
	public List<BoardDTO> restBoardSearch(HttpServletRequest req, 
			ParameterDTO parameterDTO){
		//검색어를 입력한 경우에는..
		if(req.getParameter("searchWord")!=null) {
			//폼값으로 전송한 검색어를 스페이스(공백)로 split한다. 
			String[] sTxtArray = req.getParameter("searchWord")
					.split(" ");
			//저장된 모든 데이터를 삭제한다. 
			parameterDTO.getSearchWord().clear();
			//앞에서 반환된 String 배열의 크기만큼 반복한다. 
			for(String str : sTxtArray) {
				System.out.println(str);
				//각 검색어를 하나씩 추가한다. 
				parameterDTO.getSearchWord().add(str);
			}
		}
		//Mapper의 search 메서드를 호출한다. 
		List<BoardDTO> searchList = dao.search(parameterDTO);
		return searchList;
	}
	
	//DTO를 반환하면 JSON객체 형태로 출력된다. 
	@GetMapping("/restBoardView.do")
	public BoardDTO restBoardView(ParameterDTO parameterDTO){
		BoardDTO boardDTO = dao.view(parameterDTO);
		return boardDTO;
	}

	//글쓰기 
	@PostMapping("/restBoardWrite.do")
	public Map<String, Integer> restBoardWrite(BoardDTO boardDTO){
		//글쓰기 폼에서 전송된 값은 DTO에 자동으로 저장된다.
		int result = dao.write(boardDTO);
		Map<String, Integer> map = new HashMap<>();
		//글쓰기의 결과를 JSON객체로 반환한다. 
		map.put("result", result);
		return map;
	}
}






