package com.yedam.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

/**
 * POJO(Plain Old Java Object)
 */
public class AddBoardControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String writer = request.getParameter("writer");
		String content=  request.getParameter("content");
		String title = request.getParameter("title");
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setWriter(writer);
		vo.setContent(content);
		
		BoardService boardService = new BoardServiceImpl();
		if(boardService.addBoard(vo)) {
			response.sendRedirect("boardList.do");
		}
	}

}
