package com.yedam.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
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
		request.getRequestDispatcher("WEB-INF/jsp/board.jsp")
		.forward(request, response);
	}

}
