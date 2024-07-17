package com.yedam.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.mapper.StudentMapper;
import com.yedam.vo.StudentVO;


@WebServlet("/StudentAddServ")
public class StudentAddServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public StudentAddServ() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String stdNo = request.getParameter("stdNo");
		String stdName = request.getParameter("stdName");
		String stdPhone = request.getParameter("stdPhone");
		String birthDate = request.getParameter("birthDate");
		StudentVO vo = new StudentVO();
		vo.setStdNo(stdNo);
		vo.setStdName(stdName);
		vo.setStdPhone(stdPhone);
		vo.setBirthDate(birthDate);
		
		SqlSession sqlSession = DataSource.getInstance().openSession(true);
		StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
		
		mapper.insertStudent(vo);
		
		response.sendRedirect("SampleServlet");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
