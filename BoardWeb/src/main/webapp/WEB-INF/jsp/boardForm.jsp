<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
	<h3>등록화면 (boardForm.jsp))</h3>
	<form action="addBoard.do" method="post" enctype="multipart/form-data">
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
	            <td><input class="form-control" type="text" name="writer" value="${logid }" readonly></td>
	        </tr>
	       	<tr>
	            <th>파일</th>
	            <!-- 이미지는 db에 저장하지않고 서버의 특정부분에 저장한다! -->
	            <td><input class="form-control" type="file" name="image" ></td>
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