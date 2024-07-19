package com.yedam.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.common.PageDTO;
import com.yedam.common.SearchDTO;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class BoardListControl implements Control{

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String page = request.getParameter("page");
		page = page == null ? "1" : page;
		
		String searchCondition = request.getParameter("searchCondition");
		String keyWord = request.getParameter("keyword");
		SearchDTO searchDTO = new SearchDTO();
		searchDTO.setPage(Integer.parseInt(page));
		searchDTO.setKeyword(keyWord);
		searchDTO.setSearchCondition(searchCondition);
		
		BoardService boardService = new BoardServiceImpl();
		List<BoardVO> list = boardService.boardList(searchDTO);
		request.setAttribute("boardList", list);
		
		// paging
		int totalCnt = boardService.totalCount(searchDTO);
		PageDTO pageDTO = new PageDTO(Integer.parseInt(page), totalCnt);
		
		request.setAttribute("paging", pageDTO);
		request.setAttribute("searchDTO", searchDTO);
		// 해당 페이지를 뿌려준다.
		request.getRequestDispatcher("WEB-INF/jsp/boardList.jsp")
			.forward(request, response);
	}
	
}
