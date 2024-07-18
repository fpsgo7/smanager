package com.yedam.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.mapper.StudentMapper;
import com.yedam.vo.StudentVO;

// web.xml 에 등록하여 대신사용해도된다.
//@WebServlet("/SampleServlet")// BoardWeb/SampleServlet
public class SampleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SampleServlet() {
        super();
    }
 // 서블릿의 생명 주기 파악하기
    @Override
    public void init(ServletConfig config) throws ServletException {
    	// 서버가 실행되서 호출될때 딱한번 실행된다. 새로고침해도 실행 안됨
    	System.out.println("init메소드 실행");
    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 서버가 실행중에 계속 실행된다.
    	System.out.println("service메소드 실행");
    	// 자바 의 변수를 위한 파트
    	SqlSession sqlSession = DataSource.getInstance().openSession(true);
		StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
		List<StudentVO> list = mapper.studentList();
		// 출력을 위한 파트
    	response.setContentType("text/html;charset=utf-8");
    	PrintWriter out = response.getWriter();// 데이터 출력 스트림(웹브라우져 화면)

    	out.println("<h1>Hellow World! 안녕</h1>");
		for(StudentVO svo : list){
			out.println(
					"<li><a href ='jsp/student.jsp?stdNo="
							+ svo.getStdNo() 
							+"'>"
							+ svo.getStdNo()
							+ "," 
							+ svo.getStdName()
							+"</a>");
		}
    }
    @Override
    public void destroy() {
    	System.out.println("destroy메소드 실행");
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 서블릿의 생명 주기(init() 실행 -> service() 실행 -> destroy()실행)
		// 개발자가 실행 흐름을 주도X -> WebApplicationServer(WAS) 가 주도 = 제어의 역전(Ioc)
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
