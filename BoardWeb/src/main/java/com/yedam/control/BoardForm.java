package com.yedam.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;

public class BoardForm implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// 해당 페이지를 뿌려준다.
		request.getRequestDispatcher("WEB-INF/jsp/boardForm.jsp")
			.forward(request, response);
			
	}

}
