package com.yedam.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yedam.common.Control;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;

public class PagingCount implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 글번호 => 댓글 건수
		String bno = request.getParameter("bno");
		ReplyService svc = new ReplyServiceImpl();
		
		int totalCnt = svc.replyTotalCnt(Integer.parseInt(bno));
		
		// {"totalCount": n }
		String json = String.format("{\"totalCount\" : %s }", totalCnt);
		response.getWriter().print(json);
	}

}
