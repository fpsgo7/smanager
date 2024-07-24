package com.yedam.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;

public class ScriptControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getRequestDispatcher("html/index.tiles")
			.forward(request, response);
	}

}
