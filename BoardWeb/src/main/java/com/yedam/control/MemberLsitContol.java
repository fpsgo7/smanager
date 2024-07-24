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
		
		String orderBy = request.getParameter("orderBy") != null 
				? request.getParameter("orderBy") : "member_id";
		String responsibility = request.getParameter("responsibility") != null
					? request.getParameter("responsibility") : "User";
		
		
		members = memberService.getMembers(orderBy,responsibility);
		request.setAttribute("members", members);
		request.setAttribute("orderBy", orderBy);
		request.setAttribute("responsibility", responsibility);
		
		request.getRequestDispatcher("admin/memberList.tiles")
			.forward(request, response);
	}

}
