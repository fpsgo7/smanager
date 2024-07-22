<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
	<h3>수정화면(modifyForm.jsp)</h3>
	<form action="updateBoard.do">
	<input type="hidden" name="boardNo" value="${boardVO.boardNo}" >
		<table class ="table">
	        <tr>
	            <th>제목</th>
	            <td>
	            	<input class="form-control" type="text" name="title"
	            		value="${boardVO.title}">
	            </td>
	        </tr>
	        <tr>
	            <th>내용</th>
	            <td>
	            	<textarea class="form-control" name="content">${board.content}</textarea>
	            </td>
	        </tr>
	        <tr>
	            <td colspan="2" align="center">
	            	<input class="btn btn-primary" type="submit">
	            	<input class="btn btn-secondary" type="reset">
	            </td>
	        </tr>
	    </table>
	</form>
<jsp:include page="../includes/footer.jsp"></jsp:include>