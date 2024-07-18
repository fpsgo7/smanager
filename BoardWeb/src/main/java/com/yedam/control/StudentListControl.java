package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;

public class StudentListControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// stdList.do -> 보여줄 페이지는 WEB-INF/jsp/student_list.jsp
		
		// 해당 페이지를 뿌려준다.
		try {
			request.getRequestDispatcher("WEB-INF/jsp/student_list.jsp")
				.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
