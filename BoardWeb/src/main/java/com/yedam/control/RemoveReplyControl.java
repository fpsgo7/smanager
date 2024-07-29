package com.yedam.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Control;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;

public class RemoveReplyControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String rno = request.getParameter("rno");
		ReplyService svc = new ReplyServiceImpl();
		
		Map<String, Object> map = new HashMap<>();
		try {
			if(svc.removeReply(Integer.parseInt(rno))) {
				map.put("retCode", "Success");
			}
		}catch (Exception e) {
			map.put("retCode", "Fail");
		}
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(map);
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().print(json);
	}

}
