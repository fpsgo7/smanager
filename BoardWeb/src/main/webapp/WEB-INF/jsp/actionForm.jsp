<%@page import="com.yedam.vo.StudentVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- JSTL 임포트 하기  prefix 로 사용할 태그를 정의한다. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>actionForm.jsp</title>
</head>
<body>
	<h1> 액션태그 연습. </h1>
	<%
		request.setAttribute("myName", "홍길동");
	
		StudentVO student = new StudentVO();
		student.setStdNo("100");
		student.setStdName("홍길동");
		request.setAttribute("student", student);
		
	%>
	
	<h3> JSP Standard Tag Library & Expression Language</h3>
	<h5>EL 사용해보기</h5>
	<p>${10 + 4 }</p>
	<p>${10 > 20 }</p>
	<p>${'Hellow ' += 'world' }</p>
	<p>${10 > 5 ? '참' : '거짓' }</p>
	<p>${myName}</p>
	<p>학생 객체 가져오기 </p>
	<p>전체 : ${student}</p>
	<p>학생번호 : ${student.stdNo}</p>
	<p>학생이름 : ${student.stdName}</p>

	<h3>JSTL 사용하기</h3>
	<!-- 변수 선언과 사용  -->
	<c:set var="a" value="10"></c:set>
	<p>a = ${a}</p>
	
	<!-- 조건 문 사용 -->
	<p>${a>5 ? '5보다 크다' : '5보타 작거나 같다' }</p>
	
	<c:if test="${a > 5}">
		<p>5보다 큽니다.</p>
	</c:if>
	
	<!-- if, else 문 -->	
	<c:choose>
		<c:when test="${a > 5}">
			<p>5보다 큽니다.</p>
		</c:when>
		<c:otherwise>
			<p>5보다 작거나 같습니다.</p>
		</c:otherwise>
	</c:choose>
	
	<!-- 반복문 -->
	<c:forEach var="i" begin="1" end="10" step="1" ><!-- step 증감식의 기본값은 1이다. -->
		<p>변수 i = ${i}</p>
	</c:forEach>
	
	<!-- foreach 응용 7단 출력하기 -->
	<c:forEach var = "i" begin="1" end="9" step="1">
		<p>7 * ${i} = ${7 * i}</p>
	</c:forEach>
	
</body>
</html>