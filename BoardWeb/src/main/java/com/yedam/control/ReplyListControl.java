package com.yedam.control;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Control;
import com.yedam.common.SearchDTO;
import com.yedam.service.ReplyService;
import com.yedam.service.ReplyServiceImpl;
import com.yedam.vo.ReplyVO;

public class ReplyListControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String bno = request.getParameter("bno");
		String page = request.getParameter("page");
				
		SearchDTO search = new SearchDTO();
		search.setBno(Integer.parseInt(bno));
//		search.setPage(Integer.parseInt(page));
		
		ReplyService svc = new ReplyServiceImpl();
		List<ReplyVO> list = svc.replyList(search);
		
		// datatable 연습
		Map<String, Object> map = new HashedMap();
		map.put("data", list);
		
		// json 문자열 방식
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(map);
		response.setContentType("text/json;charset=utf-8");
		
		response.getWriter().print(json);
	}

}
