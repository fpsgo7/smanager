package com.yedam.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.common.SearchDTO;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class RemoveBoardForm implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String boardNo = request.getParameter("boardNo");
		
		BoardService boardService = new BoardServiceImpl();
		BoardVO board = boardService.getBoard(Integer.parseInt(boardNo));
		
		request.setAttribute("boardVO", board);
		
		SearchDTO searchDTO = new SearchDTO();
		searchDTO.setPage(Integer.parseInt(request.getParameter("page")));
		searchDTO.setKeyword(request.getParameter("keyword"));
		searchDTO.setSearchCondition(request.getParameter("searchCondition"));
		request.setAttribute("search", searchDTO);
		
		request.getRequestDispatcher("board/removeForm.tiles")
			.forward(request, response);

	}

}
