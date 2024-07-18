package com.yedam.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class BoardListControl implements Control{

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setAttribute("myName", "박종원");
		
		BoardService boardService = new BoardServiceImpl();
		List<BoardVO> list = boardService.boardList();
		request.setAttribute("boardList", list);
		// 해당 페이지를 뿌려준다.
		request.getRequestDispatcher("WEB-INF/jsp/boardList.jsp")
			.forward(request, response);
	}
	
}
