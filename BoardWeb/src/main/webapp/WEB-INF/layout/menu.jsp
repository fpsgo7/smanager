<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix = "c" %>
<div class="border-end bg-white" id="sidebar-wrapper">
<c:choose>
	<c:when test="${empty logid}">
		<div class="sidebar-heading border-bottom bg-light">로그인 해주세요</div>
	</c:when>
	<c:otherwise>
		<div class="sidebar-heading border-bottom bg-light">${logid}</div>
	</c:otherwise>
</c:choose>
    <div class="list-group list-group-flush">
        <a class="list-group-item list-group-item-action list-group-item-light p-3" href="boardList.do">게시글 목록</a>
        <a class="list-group-item list-group-item-action list-group-item-light p-3" href="action.do">jsp 태그연습</a>
        <c:choose>
 		<c:when test="${empty logid}">
 			<a class="list-group-item list-group-item-action list-group-item-light p-3" href="loginForm.do">로그인</a>
 		</c:when>
 		<c:otherwise>
 			<a class="list-group-item list-group-item-action list-group-item-light p-3" href="boardForm.do">등록화면</a>
 			<a class="list-group-item list-group-item-action list-group-item-light p-3" href="logout.do">로그아웃</a>
 		</c:otherwise>
		</c:choose>
        <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">Profile</a>
        <a class="list-group-item list-group-item-action list-group-item-light p-3" href="#!">Status</a>
    </div>
</div>