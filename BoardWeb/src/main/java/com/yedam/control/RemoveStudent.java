package com.yedam.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.MemberService;
import com.yedam.service.MemberServiceImpl;

public class RemoveStudent implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String sno = request.getParameter("sno");
		MemberService svc = new MemberServiceImpl();
		if(svc.removeStudent(sno)) {
			// 삭제 성공시 {"retCode" : "Success"}
			response.getWriter().print("{\"retCode\" : \"Success\"}");
		}else {
			// 삭제 실패시 {"retCode" : "Fail"}
			response.getWriter().print("{\"retCode\" : \"Fail\"}");
		}
		
	}

}
