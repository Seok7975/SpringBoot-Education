package com.edu.springboot.restboard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import utils.PagingUtil;

//메서드에서 모든 반환값은 웹브라우저에 즉시 출력된다. 
@Controller
public class BoardController {
	
	//인터페이스로 정의된 DAO빈 자동주입 
	@Autowired
	IBoardService dao;
	
	/* board 테이블의 레코드를 각 페이지별로 구분해서 배열 형태로 출력한다.
	페이지 번호가 없는 경우 1페이지의 내용을 출력하게된다. */
	@GetMapping("/boardList.do")
	public ModelAndView restBoardList(ParameterDTO parameterDTO, HttpServletRequest req){
		
		int totalCount = dao.totalCount();
		int pageSize = 10;
		int blockPage = 5;
		//페이지 번호 가져오기
		int pageNum = parameterDTO.getPageNum()==null ? 1 :
			Integer.parseInt(parameterDTO.getPageNum());
		//게시물의 구간 계산 
		int start = (pageNum-1) * pageSize + 1;
		int end = pageNum * pageSize;
		parameterDTO.setStart(start);
		parameterDTO.setEnd(end);
		List<BoardDTO> boardList = dao.list(parameterDTO);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardList", boardList);
		mv.setViewName("board/list");
		
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("totalCount", totalCount);
		maps.put("pageSize", pageSize);
		maps.put("pageNum", pageNum);
		mv.addObject("maps", maps);
		
		String pagingImg =
				PagingUtil.pagingImg(totalCount, pageSize, 
					blockPage, pageNum,
					req.getContextPath()+"/boardList.do?");
		mv.addObject("pagingImg", pagingImg);
		
		return mv;
	}
	
	@GetMapping("/boardView.do")
	public ModelAndView restBoardView(ParameterDTO parameterDTO){
		BoardDTO boardDTO = dao.view(parameterDTO);
		boardDTO.setContent(boardDTO.getContent().replace("\r\n","<br>"));
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardDTO", boardDTO);
		mv.setViewName("board/view");
		
		return mv;
	}

	@GetMapping("/boardWrite.do")
	public ModelAndView restBoardWrite() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/write");
		
		return mv;
	}
	
	@PostMapping("/boardWrite.do")
	public String restBoardWrite(BoardDTO boardDTO){
		int result = dao.write(boardDTO);		
		return "redirect:boardList.do";
	}
}
