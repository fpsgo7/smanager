<%@page import="com.yedam.vo.StudentVO"%>
<%@page import="com.yedam.common.DataSource"%>
<%@page import="com.yedam.mapper.StudentMapper"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		SqlSession sqlSession = DataSource.getInstance().openSession(true);
		StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
		String sno  
			// 주소 파라미터  ?stdNo=S2024-02 
			= request.getParameter("stdNo");
		StudentVO std = mapper.student(sno);
	%>
	<h3>학생 상새보기</h3>
	<table border="2">	
		<tr>
			<th>학생번호</th><td><%= std.getStdNo() %></td>	
		</tr>
		<tr>
			<th>이름</th><td><%= std.getStdName() %></td>
		</tr>
		<tr>		
			<th>연락처</th><td><%= std.getStdPhone() %></td>
		</tr>
		<tr>		
			<th>주소</th><td><%= std.getAddress() %></td>
		</tr>
	</table>
	<a href="student_list.jsp"> 학생목록으로 이동...</a>
</body>
</html>