package com.yedam.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoardServiceImpl;
import com.yedam.vo.ScheduleVO;

public class Fullcalendar implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 예시데이터 올리기
		// "[{"title":"sample", "start":"2023-01-05"},{"title":"회의", "start":"2023-01-21T13:00:00","end":"2023-01-21T16:00:00"}]"
		//response.getWriter().print("[{\"title\":\"sample\", \"start\":\"2023-01-05\"},{\"title\":\"회의\", \"start\":\"2023-01-21T13:00:00\",\"end\":\"2023-01-21T16:00:00\"}]");
		
		BoardService boardService = new BoardServiceImpl();
		// DB의 컬럼과, 자바스크립트의 컬럼이 서로 같지 않기때문에
		// DB 에 대응되는 Schedule 객체대신 다른 것을 사용하였다.
		List<ScheduleVO> list = boardService.getSchedule();
		
		// json 문자열 방식
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(list);
		System.out.println(json);
		
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().print(json);
	}

}
