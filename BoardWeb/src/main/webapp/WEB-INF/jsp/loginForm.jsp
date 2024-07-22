<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../includes/header.jsp"></jsp:include>

<h3>로그인 화면</h3>

<!--${msg !=null} -->
<c:if test="${!empty msg}">
	<p>${msg }</p>
</c:if>

<form action="login.do">
<table class="table">
	<tr>
		<td>아이디</td>
		<td><input type="text" class="form-control" name="id"></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" class="form-control" name="pw"></td>
	</tr>
	<tr>
		<td colspan = "2" align="center">
			<input type="submit" value="로그인" class="btn btn-primary">
		</td>
	</tr>
</table>
</form>
<jsp:include page="../includes/footer.jsp"></jsp:include>