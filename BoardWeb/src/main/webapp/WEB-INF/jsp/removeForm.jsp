<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
	<h3>삭제화면(remobeForm.jsp)</h3>
	<form action="deleteBoard.do">
	<input type="hidden" name="boardNo" value="${boardVO.boardNo}" >
	<input type="hidden" name="page" value="${search.page}" >
	<input type="hidden" name="searchCondition" value="${search.searchCondition}" >
	<input type="hidden" name="keyword" value="${search.keyword}" >
	<table class ="table">
	    <tr>
            <th>글번호</th>
            <td>${boardVO.boardNo}</td>
            <th>조회수</th>
            <td>${boardVO.viewCnt}</td>
        </tr>
        <tr>
            <th>제목</th>
            <td colspan="3">${boardVO.title}</td>
        </tr>
        <tr>
            <th>내용</th>
            <td colspan="3">${boardVO.content}</td>
        </tr>
        <tr>
            <th>작성자</th>
            <td colspan="3">${boardVO.writer}</td>
        </tr>
        <tr>
            <td colspan="2" align="center">
            	<input class="btn btn-danger" type="submit" value="삭제">
            	<button class="btn btn-warning" type="button" >수정</button>
            </td>
        </tr>
    </table>
	</form>
<jsp:include page="../includes/footer.jsp"></jsp:include>