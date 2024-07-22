package com.yedam.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
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
		
		String writer; 
		String content;
		String title; 
		
		// 파일전송 multipart/form-data 방식. cos라이브러리
		String savePath= request.getServletContext().getRealPath("images");// 서버상 최상위 경로 getServletContext에서 시작하자
		int maxSize = 1024*1024*5;
		// 1. 파일 업로드(images)
		MultipartRequest multipartRequest = new MultipartRequest(
				request,// 1. 요청정보
				savePath, // 2. 업로드 경로
				maxSize, // 3. 파일의 최대크기
				"utf-8", //  4. 파일명 해석 인코딩
				new DefaultFileRenamePolicy()// 5. 리네임 정책
				);
		writer = multipartRequest.getParameter("writer");
		content=  multipartRequest.getParameter("content");
		title = multipartRequest.getParameter("title");
		String image = multipartRequest.getFilesystemName("image");
		// 2. 파일에 관한 정보를 DB에 입력
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setWriter(writer);
		vo.setContent(content);
		vo.setImage(image);
		System.out.println(image);
		
		BoardService boardService = new BoardServiceImpl();
		if(boardService.addBoard(vo)) {
			response.sendRedirect("boardList.do");
		}
	}

}
