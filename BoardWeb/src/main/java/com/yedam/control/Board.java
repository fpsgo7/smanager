package com.yedam.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.Control;
import com.yedam.common.SearchDTO;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class Board implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String boardNo = request.getParameter("boardNo");
		
		BoardService boardService = new BoardServiceImpl();
		BoardVO board = boardService.getBoard(Integer.parseInt(boardNo));
		request.setAttribute("board", board);
		
		// 해당 글의 접속하기전 search 정보를 세션에 올린다
		SearchDTO searchDTO = new SearchDTO();
		searchDTO.setPage(Integer.parseInt(request.getParameter("page")));
		searchDTO.setKeyword(request.getParameter("keyword"));
		searchDTO.setSearchCondition(request.getParameter("searchCondition"));
		request.setAttribute("search", searchDTO);
		
		request.getRequestDispatcher("WEB-INF/jsp/board.jsp")
		.forward(request, response);
	}

}
