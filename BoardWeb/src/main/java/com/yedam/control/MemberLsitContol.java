package com.yedam.control;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.MemberService;
import com.yedam.service.MemberServiceImpl;
import com.yedam.vo.MemberVO;

public class MemberLsitContol implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<MemberVO> members = new ArrayList<MemberVO>();
		MemberService memberService = new MemberServiceImpl();
		
		String orderBy = request.getParameter("orderBy");
		
		members = memberService.getMembers(orderBy);
		request.setAttribute("members", members);
		
		request.getRequestDispatcher("admin/memberList.tiles")
			.forward(request, response);
	}

}
