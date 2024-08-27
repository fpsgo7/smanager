package com.yedam.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.Control;
import com.yedam.service.MemberService;
import com.yedam.service.MemberServiceImpl;

public class SignOutControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		Map<String,String> map = new HashMap<>();
		map.put("memberId", (String)session.getAttribute("logid"));
		
		MemberService svc = new MemberServiceImpl();
		svc.signOutProc(map);
		
		System.out.println("code" + map.get("retCode"));
		System.out.println("msg" + map.get("retMsg"));
		
		session.invalidate();// 회원 탈퇴 했기 때문에 탈퇴한 계정의 세션값도 제거해야한다.
		
		response.sendRedirect("boardList.do");
	}

}
