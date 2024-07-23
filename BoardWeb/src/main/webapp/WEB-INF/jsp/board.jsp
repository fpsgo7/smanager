<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix = "c" %>
	<h3>상세화면(board.do)</h3>
	<form action="removeBoard.do">
	<input type="hidden" name="boardNo" value="${board.boardNo}" >
	<input type="hidden" name="page" value="${search.page}" >
	<input type="hidden" name="searchCondition" value="${search.searchCondition}" >
	<input type="hidden" name="keyword" value="${search.keyword}" >
	<table class ="table">
	    <tr>
            <th>글번호</th>
            <td>${board.boardNo}</td>
            <th>조회수</th>
            <td>${board.viewCnt}</td>
        </tr>
        <tr>
            <th>제목</th>
            <td colspan="3">${board.title}</td>
        </tr>
        <tr>
            <th>내용</th>
            <td colspan="3">${board.content}</td>
        </tr>
        <tr>
            <th>작성자</th>
            <td colspan="3">${board.writer}</td>
        </tr>
        <tr>
        	<th>파일</th>
        	<td colspan="3">
        		<img width="250px" src="images/${board.image}">
        	</td> 
        </tr>
        <tr>
        	<c:choose>
        		<c:when test="${board.writer == logid}">
	        		<td colspan="2" align="center">
	            		<input class="btn btn-danger" type="submit" value="삭제">
	            		<button class="btn btn-warning" type="button" >수정</button>
	            	</td>
        		</c:when>
        		<c:otherwise>
        			<td colspan="2" align="center">
	            		<input class="btn btn-danger" disabled type="submit" value="삭제">
	            		<button class="btn btn-warning" disabled type="button" >수정</button>
	            	</td>
        		</c:otherwise>
        	</c:choose>
        </tr>
    </table>
	</form>
	<script>
		document.querySelector('form>table button.btn.btn-warning')
			.addEventListener('click', function(e){
				location.href = 'modifyBoard.do?boardNo=${board.boardNo}';
			});
	</script>