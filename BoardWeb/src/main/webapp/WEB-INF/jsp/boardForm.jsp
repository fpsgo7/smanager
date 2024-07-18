<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../includes/header.jsp" %>
	<h3>등록화면</h3>
	<form action="addBoard.do">
		<table class ="table">
	        <tr>
	            <th>제목</th>
	            <td><input class="form-control" type="text" name="title"></td>
	        </tr>
	        <tr>
	            <th>내용</th>
	            <td><textarea class="form-control" name="content"></textarea></td>
	        </tr>
	        <tr>
	            <th>작성자</th>
	            <td><input class="form-control" type="text" name="writer"></td>
	        </tr>
	        <tr>
	            <td colspan="2" align="center">
	            	<input class="btn btn-primary" type="submit">
	            	<input class="btn btn-secondary" type="reset">
	            </td>
	        </tr>
	    </table>
	</form>
<%@ include file="../includes/footer.jsp" %>