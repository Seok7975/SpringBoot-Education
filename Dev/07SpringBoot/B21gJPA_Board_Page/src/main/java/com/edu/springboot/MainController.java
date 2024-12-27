package com.edu.springboot;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.edu.springboot.jpaboard.BoardEntity;
import com.edu.springboot.jpaboard.BoardService;

import jakarta.servlet.http.HttpServletRequest;
import utils.PagingUtil;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String home() {	
		return "main";
	}
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("/list.do")
	public String list(Model model, HttpServletRequest req) {
		
		String searchWord = req.getParameter("searchWord");
		String pageNum = req.getParameter("pageNum");
		System.out.println("검색어="+ searchWord);
		System.out.println("페이지번호="+ pageNum);
		
		Sort sort = Sort.by(Sort.Order.desc("idx"));
		int iPageNum = (pageNum==null) ? 0 : Integer.parseInt(pageNum) - 1;
		
		Pageable pageable = PageRequest.ofSize(5).withPage(iPageNum).withSort(sort);
		
		Page<BoardEntity> boardResult ;
		if(searchWord==null || searchWord.equals("")) {
			System.out.println("검색없음");
			boardResult = boardService.selectListPage(pageable); 
		}
		else {			
			System.out.println("검색있음");
			searchWord = "%"+ searchWord +"%";
			boardResult = boardService.selectListPageSearch(searchWord, pageable);		
		}
		List<BoardEntity> rows = boardResult.getContent();
		
		//레코드 갯수
		int totalElements = (int)(boardResult.getTotalElements());
		//전체 페이지수
//		int totalPages = boardResult.getTotalPages();
		//페이지 당 레코드 수
		int pageSize = boardResult.getSize();
		//현재 페이지 번호. 0부터 시작이므로 1을 더해줌 
//		int pageNumber = boardResult.getNumber() + 1; 
		//컨텐츠의 갯수
//		int numberOfElements = boardResult.getNumberOfElements();
				
		String pagingImg =
			PagingUtil.pagingImg(totalElements, pageSize, 
				2, iPageNum+1, req.getContextPath()+"/list.do?");
		
		model.addAttribute("pagingImg", pagingImg);
		model.addAttribute("rows", rows);		
		
		return "boardList";
	}
	
	@GetMapping("/write.do")
	public String write() {	
		return "boardWrite";
	}
	
	@PostMapping("/writeProc.do")
	public String writeProc(BoardEntity boardTable) {
		boardService.insertPost(boardTable);
		return "redirect:list.do";
	}
	
	@GetMapping("/view.do")
	public String view(Model model, HttpServletRequest req) {
		String idx = req.getParameter("idx");
		Optional<BoardEntity> result = boardService.selectPost(Long.parseLong(idx));
		if (result.isPresent()) {
			BoardEntity be = result.get();
			be.setContents(be.getContents().replaceAll("\r\n", "<br>"));
       		model.addAttribute("row", be);
        }
        else {
       		model.addAttribute("row", null);
        }
		return "boardView";
	}
	
	@GetMapping("/edit.do")
	public String edit(Model model, HttpServletRequest req) {
		String idx = req.getParameter("idx");
		Optional<BoardEntity> result = boardService.selectPost(Long.parseLong(idx));
		if (result.isPresent()) {
       		model.addAttribute("row", result.get());
        }
        else {
       		model.addAttribute("row", null);
        }
		return "boardEdit";
	}
	
	@PostMapping("/editProc.do")
	public String editProc(BoardEntity boardTable) {
		boardService.updatePost(boardTable);
		return "redirect:view.do?idx="+ boardTable.getIdx();
	}
	
	@GetMapping("/delete.do")
	public String delete(BoardEntity boardTable) {
		boardService.deletePost(boardTable.getIdx());
		return "redirect:list.do";
	}
	
}




