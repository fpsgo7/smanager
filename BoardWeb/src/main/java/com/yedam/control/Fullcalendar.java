package com.yedam.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;

public class Fullcalendar implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// "[{"title":"sample", "start":"2023-01-05"},{"title":"회의", "start":"2023-01-21T13:00:00","end":"2023-01-21T16:00:00"}]"
		
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().print("[{\"title\":\"sample\", \"start\":\"2023-01-05\"},{\"title\":\"회의\", \"start\":\"2023-01-21T13:00:00\",\"end\":\"2023-01-21T16:00:00\"}]");
	}

}
