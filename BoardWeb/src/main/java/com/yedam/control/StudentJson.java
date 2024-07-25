package com.yedam.control;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.MemberService;
import com.yedam.service.MemberServiceImpl;
import com.yedam.vo.StudentVO;

public class StudentJson implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/json;charset=utf-8");
		// 학생 목록을 json 문자열로 표현
		// [{"stdNo": "2024-002" , "stdName" : 홍길동 ... },{...},{...}
		String json = ""; // json 문자열을 담기
		MemberService svc = new MemberServiceImpl();
		List<StudentVO> list = svc.studentList();
		
		json += "[";
		for(int i = 0; i<list.size(); i++) {
			json += String.format(
					"{\"stdNo\": \"%s\""
					+ ",\"stdName\":\"%s\""
					+ ",\"stdPhone\":\"%s\""
					+ "}", 
					list.get(i).getStdNo(),
					list.get(i).getStdName(),
					list.get(i).getStdPhone()
					);
			if(i != list.size() - 1) {
				json += ",";
			}
		}
		json += "]";
		// 자바스크립트에서 해당 내용을 받는다.
		response.getWriter().print(json);
		
	}

}
