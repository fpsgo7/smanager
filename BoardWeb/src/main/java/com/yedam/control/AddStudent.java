package com.yedam.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.common.Control;
import com.yedam.service.MemberService;
import com.yedam.service.MemberServiceImpl;
import com.yedam.vo.StudentVO;

public class AddStudent implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sno = request.getParameter("sno");
		String sname = request.getParameter("sname");
		String phone = request.getParameter("phone");
		
		StudentVO  svo = new StudentVO();
		svo.setStdNo(sno);
		svo.setStdName(sname);
		svo.setStdPhone(phone);
		svo.setBirthDate("19990305");
		
		MemberService svc = new MemberServiceImpl();
		Gson gson = new GsonBuilder().create();
		Map<String, Object> map = new HashMap<>();
		if(svc.addStudent(svo)) {
			map.put("retCode", "Success");
			map.put("retVal", svo);
			response.getWriter().print(
						gson.toJson(map)
					);
		}else {
			map.put("retCode", "Fail");
			map.put("retVal", null);
			response.getWriter().print(
						gson.toJson(map)
					);
		}
	}

}
