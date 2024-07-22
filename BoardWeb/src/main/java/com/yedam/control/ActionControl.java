package com.yedam.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;

public class ActionControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getRequestDispatcher("WEB-INF/jsp/actionForm.jsp")
		.forward(request, response);
	}

}
