package com.yedam.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.ScheduleVO;

public class AddScheduleControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String title = request.getParameter("title");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		
		ScheduleVO scheduleVO = new ScheduleVO();
		scheduleVO.setTitle(title);
		scheduleVO.setStart(start);
		scheduleVO.setEnd(end);
		
		BoardService boardService = new BoardServiceImpl();
		Map<String, Object> map = new HashMap<>();
		if(boardService.addSchedule(scheduleVO)) {
			map.put("retCode", "Success");
		}else {
			map.put("retCode", "Faild");
		}
		// Map 컬렉션을 json 화 시키기
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(map);
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().print(json);
	}

}
