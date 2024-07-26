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
import com.yedam.vo.ReplyVO;

public class AddReplyControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String replyer = request.getParameter("replyer");
		String content = request.getParameter("content");
		String bno = request.getParameter("bno");
		ReplyService svc = new ReplyServiceImpl();
		ReplyVO rvo = new ReplyVO();
		rvo.setReplyer(replyer);
		rvo.setReplyContent(content);
		rvo.setBoardNo(Integer.parseInt(bno));
		// retCode: Success, retVal: ReplyVO
		// retCode: Fail, retVal: null
		Map<String, Object> map = new HashMap<>();
		try {
			if(svc.addRply(rvo)) {
				map.put("retCode", "Success");
				map.put("retVal", rvo);
			}
		}catch(Exception e) {
			map.put("retCode", "Fail");
			map.put("retVal", null);
		}
		// Map 컬렉션을 json 화 시키기
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(map);
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().print(json);
	}

}
