package com.yedam.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.Control;
import com.yedam.service.MemberService;
import com.yedam.service.MemberServiceImpl;
import com.yedam.vo.MemberVO;

public class LoginControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// id, pw 파라미터 => 게시글 목록 OR 로그인화면
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberService svc = new MemberServiceImpl();
		MemberVO mem = svc.loginCheck(id, pw);
		if(mem == null) {
			// msg 를 "아이디와 비밀번호를 확인하세요 담기"
			request.setAttribute("msg", "아이디와 비번을 확인하세요!");
			request.getRequestDispatcher("board/loginForm.tiles")
			.forward(request, response);
			return;
		}
		
		// 세션에 값추가
		HttpSession session = request.getSession(); // 새션 객체 가져오기
		session.setAttribute("logid", id);// 새션에 값 추가
		session.setMaxInactiveInterval(30 * 60);// 세션 기한 (초단위) / 0은 기한이 무한대가 된다.
		if(mem.getResponsibility().equals("User")) {
			response.sendRedirect("boardList.do");
		}
		else if(mem.getResponsibility().equals("Admin")){
			response.sendRedirect("memberList.do");
		}else {
			System.out.println("없는 권한입니다.");
		}

	}

}
