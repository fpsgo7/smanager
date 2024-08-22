package com.yedam.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.control.ActionControl;
import com.yedam.control.AddBoardControl;
import com.yedam.control.AddReplyControl;
import com.yedam.control.AddStudent;
import com.yedam.control.BoardControl;
import com.yedam.control.BoardForm;
import com.yedam.control.BoardListControl;
import com.yedam.control.CntByMember;
import com.yedam.control.DeleteBoard;
import com.yedam.control.Fullcalendar;
import com.yedam.control.GoogleChart;
import com.yedam.control.ImageDownLoad;
import com.yedam.control.LoginControl;
import com.yedam.control.LoginForm;
import com.yedam.control.LogoutControl;
import com.yedam.control.MemberLsitContol;
import com.yedam.control.StudentListControl;
import com.yedam.control.UpdateBoard;
import com.yedam.control.RemoveReplyControl;
import com.yedam.control.ModifyBoardForm;
import com.yedam.control.PagingCount;
import com.yedam.control.RemoveBoardForm;
import com.yedam.control.RemoveStudent;
import com.yedam.control.ReplyListControl;
import com.yedam.control.ScriptControl;
import com.yedam.control.StudentJson;

/**
 * FrontController 역할은 사용자의 모든 요청을 처리한다.
 * 서블릿. a.do , sample.do .do 로 끝나는 요청을 받는다.
 * 서블릿 생명 주기 : 객체 생성 -> init -> service -> destory
 */
public class FrontController extends HttpServlet{
	Map<String, Control> map;
	public FrontController() {
		map  = new HashMap<String, Control>();
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		map.put("/boardList.do", new BoardListControl());
		// 글등록 구현 : 등록 화면(boardForm.do) + DB 등록(addBoard.do) ->글 목록 페이지로 이동(boardList.do)
		map.put("/boardForm.do", new BoardForm());
		map.put("/addBoard.do", new AddBoardControl());
		map.put("/board.do", new BoardControl());
		map.put("/removeBoard.do", new RemoveBoardForm());// 삭제 페이지
		map.put("/deleteBoard.do", new DeleteBoard());// 삭제 작업
		map.put("/modifyBoard.do", new ModifyBoardForm());
		map.put("/updateBoard.do", new UpdateBoard());
		// 댓글 파트 (글관련하여)
		map.put("/replyList.do", new ReplyListControl());
		map.put("/addReply.do", new AddReplyControl());
		map.put("/removeReply.do", new RemoveReplyControl());
		map.put("/pagingCount.do", new PagingCount());
		// 학생 파트
		map.put("/stdList.do", new StudentListControl());
		// 공부 파트
		map.put("/action.do", new ActionControl());
		// 로그인 파트
		map.put("/loginForm.do", new LoginForm());
		map.put("/login.do", new LoginControl());
		map.put("/logout.do", new LogoutControl());
		// 관리자 파트
		map.put("/memberList.do", new MemberLsitContol());
		
		// 자바스크립트 연습하는 페이지 호출
		map.put("/javascript.do", new ScriptControl());
		// json 활용하기
		map.put("/studentJson.do", new StudentJson());
		map.put("/removeStudent.do", new RemoveStudent());
		map.put("/addStudent.do", new AddStudent());
		
		// 이미지 다운로드
		map.put("/imageDownload.do", new ImageDownLoad());
		// 작성자별 게시글 건수.
		map.put("/countByMember.do", new CntByMember());
		map.put("/googleChart.do", new GoogleChart());
		
		// fullcalendar 사용
		map.put("/fullcalendar.do", new Fullcalendar());
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 여러 .do 호출을 위한 문장 작성하기
		// URL(http://localhost/BoardWeb/*.do) 전부 가져온다.
		// URI (/BoardWeb/*.do  )해당 부분만 가져온다.
		String uri = request.getRequestURI(); // uri 가져옴
		String context = request.getContextPath();// 프로젝트명 /BoardWeb 
		String path = uri.substring(context.length()); // "/*.do"
		
//		System.out.printf("%s \n %s \n %s \n", uri, context, path);
		
		Control sub = map.get(path);
		try {
			sub.exec(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void destroy() {
		System.out.println("destroy() 호출");
	}
}
