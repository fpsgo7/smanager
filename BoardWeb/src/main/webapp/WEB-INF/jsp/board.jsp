<%@page import="com.yedam.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>
<%
	BoardVO board = (BoardVO)request.getAttribute("boardVO");
%>
	<h3>상세화면(board.do)</h3>
	<form action="removeBoard.do">
	<input type="hidden" name="boardNo" value="<%=board.getBoardNo()%>" >
	<table class ="table">
	    <tr>
            <th>글번호</th>
            <td><%=board.getBoardNo() %></td>
            <th>조회수</th>
            <td><%=board.getViewCnt() %></td>
        </tr>
        <tr>
            <th>제목</th>
            <td colspan="3"><%=board.getTitle() %></td>
        </tr>
        <tr>
            <th>내용</th>
            <td colspan="3"><%=board.getContent() %></td>
        </tr>
        <tr>
            <th>작성자</th>
            <td colspan="3"><%=board.getWriter() %></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
            	<input class="btn btn-danger" type="submit" value="삭제">
            	<button class="btn btn-warning" type="button" >수정</button>
            </td>
        </tr>
    </table>
	</form>
	<script>
		document.querySelector('form>table button.btn.btn-warning')
			.addEventListener('click', function(e){
				location.href = 'modifyBoard.do?boardNo=<%=board.getBoardNo()%>';
			});
	</script>
<%@ include file="../includes/footer.jsp" %>