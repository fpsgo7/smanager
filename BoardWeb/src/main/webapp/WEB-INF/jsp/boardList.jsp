<%@page import="com.yedam.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// jsp 에서는 내장객체로 들어 있어서 사용가능하다.
	String name = (String)request.getAttribute("myName");

	List<BoardVO> list = (List<BoardVO>)request.getAttribute("boardList");
%>
<%@ include file="../includes/header.jsp" %>
	<h3>게시글 목록(boardList.jsp)</h3>
	<p>너의 이름은 <%=name %></p>
	<table class="table">
		<thead>
			<tr>
				<th> 글번호 </th>
				<th> 제  목</th>
				<th> 작성자</th>
				<th> 작성일시</th>
			</tr>
	
		</thead>
		<tbody>
			<% for (BoardVO board : list) { %>
			<tr>
				<td><%=board.getBoardNo()%></td>
				<td><%=board.getTitle()%></td>
				<td><%=board.getWriter()%></td>
				<td><%=board.getWriteDate()%></td>
			</tr>
			<% }%>
		</tbody>
	</table>
<%@ include file="../includes/footer.jsp" %>