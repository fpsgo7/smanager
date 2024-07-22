package com.yedam.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.Control;

public class LogoutControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 새션 객체의 정보를 삭제하거나 setAttribute("logid", id); // logid 에 새로운 값을 넣어도된다.
		HttpSession session = request.getSession();
		session.invalidate(); // 새션객체의 정보를 삭제
		
		response.sendRedirect("loginForm.do");
	}

}
