<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h3>회원목록 ... </h3>
<ul class="nav nav-pills">
	<c:choose>
		<c:when test="${responsibility eq 'Admin'}">
		  	<li class="nav-item">
		    	<a class="nav-link active" aria-current="responsibility" href="memberList.do?responsibility=User&orderBy=${orderBy }">회원</a>
		  	</li>
		  	<li class="nav-item">
		    	<a class="nav-link" href="#">관리자</a>
		  	</li>
		</c:when>
		<c:otherwise>
			<li class="nav-item">
		    	<a class="nav-link" href="#">회원</a>
		  	</li>
		  	<li class="nav-item">
		  		<a class="nav-link active" aria-current="responsibility" href="memberList.do?responsibility=Admin&orderBy=${orderBy }">관리자</a>
		  	</li>
		</c:otherwise>
	</c:choose>
</ul>
<table class="table table-hover table-success">
	<thead>
		<tr>
			<th scope="col" >#</th>
			<th scope="col" ><a href="memberList.do?responsibility=${responsibility }&orderBy=member_id"> 아이디 </a></th>
			<th scope="col" > 비밀번호 </th>
			<th scope="col" ><a href="memberList.do?responsibility=${responsibility }&orderBy=member_nm"> 이름  </a></th>
			<th scope="col" > 권한등급 </th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="member" items="${members}" varStatus="stat">
		<tr>
			<th scope = "row">${stat.count}</th>
			<td>${member.memberId}</td>
			<td>${member.memberPw}</a></td>
			<td>${member.memberNm}</td>
			<td>${member.responsibility}</td>
		</tr>
		</c:forEach>
	</tbody>
</table>