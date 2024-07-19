package com.yedam.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;

public class DeleteBoard implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String boardNo = request.getParameter("boardNo");
		
		BoardService service = new BoardServiceImpl();
		if(service.removeBoard(Integer.parseInt(boardNo))) {
			// 삭제 성공시 목록으로 이동
			response.sendRedirect("boardList.do");
		}else {
			// 삭제 실패시 삭제 페이지로 이동
			response.sendRedirect("removeBoard.do?boardNo="+boardNo);
		}

	}

}
