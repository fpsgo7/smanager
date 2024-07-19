package com.yedam.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class ModifyBoardForm implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String boardNo = request.getParameter("boardNo");
		
		BoardService boardService = new BoardServiceImpl();
		BoardVO board = boardService.getBoard(Integer.parseInt(boardNo));
		
		request.setAttribute("boardVO", board);
		
		request.getRequestDispatcher("WEB-INF/jsp/modifyForm.jsp").forward(request, response);

	}

}
