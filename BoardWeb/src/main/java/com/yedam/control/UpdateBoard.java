package com.yedam.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.BoardVO;

public class UpdateBoard implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String boardNo = request.getParameter("boardNo");
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle(title);
		boardVO.setContent(content);
		boardVO.setBoardNo(Integer.parseInt(boardNo));
		
		BoardService service = new BoardServiceImpl();
		
		if(service.modifyBoard(boardVO)) {
			// 수정 성공시 리스트로 이동
			response.sendRedirect("boardList.do");
		}else {
			// 수정 실패시 다시 수정화면으로 이동
			response.sendRedirect("modifyBoard.do?boardNo="+boardNo);
		}
		
	}

}
