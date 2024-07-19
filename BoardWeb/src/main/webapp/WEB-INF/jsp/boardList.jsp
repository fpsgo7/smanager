<%@page import="com.yedam.common.SearchDTO"%>
<%@page import="com.yedam.common.PageDTO"%>
<%@page import="com.yedam.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// jsp 에서는 내장객체로 들어 있어서 사용가능하다.
	String name = (String)request.getAttribute("myName");
	List<BoardVO> list = (List<BoardVO>)request.getAttribute("boardList");
	PageDTO paging = (PageDTO)request.getAttribute("paging");
	SearchDTO searchDTO = (SearchDTO)request.getAttribute("searchDTO");
%>
<%@ include file="../includes/header.jsp" %>
	<h3>게시글 목록(boardList.jsp)</h3>
	<!-- 검색 기능 -->
	<div class="center">
		<form action = "boardList.do">
			<div class = "row">
				<div class = "col-sm-4"><!-- select 목록 -->
					<select name="searchCondition" class="form-control">
						<option value="">선택하세요.</option>
						<option value="T">제목</option>
						<option value="W">작성자</option>
						<option value="TW">제목 & 작성자</option>
					</select>
				</div>
				<div class="col-sm-6">
					<input type="text" name="keyword" class="form-control">
				</div>
				<div class="col-sm-2">
					<input type="submit" value="조회" class="btn btn-primary">
				</div>
			</div>
		</form>
	</div>
	
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
				<td><a href="board.do?boardNo=<%=board.getBoardNo()%>"><%=board.getTitle()%></a></td>
				<td><%=board.getWriter()%></td>
				<td><%=board.getWriteDate()%></td>
			</tr>
			<% }%>
		</tbody>
	</table>
	<!-- 페이징 부분 -->
	<p><%=paging %></p>
	<nav aria-label="Page navigation example">
	  <ul class="pagination justify-content-center">
	  	<!-- prev 페이지  -->
	  	<%if(paging.isPrev()){ %>
	    <li class="page-item">
	      <a class="page-link" href="boardList.do?page=<%=paging.getStartPage()-1 %>&searchCondition=<%=searchDTO.getSearchCondition() %>&keyword=<%=searchDTO.getKeyword()%>" aria-label="Previous">
	        <span aria-hidden="true">&laquo;</span>
	      </a>
	    </li>
	    <%} %>
	    <!-- 페이지 개수 만큼 링크 생성 -->
	    <%for(int p = paging.getStartPage(); p <= paging.getEndPage(); p++){ 
	    	if(p == paging.getPage()){%>
	    <li class="page-item active">
	    	<a class="page-link" href="boardList.do?page=<%=p%>&searchCondition=<%=searchDTO.getSearchCondition() %>&keyword=<%=searchDTO.getKeyword()%>"><%=p %></a>
	    </li>
	    	<%}else{%>
	    <li class="page-item">
	    	<a class="page-link" href="boardList.do?page=<%=p%>&searchCondition=<%=searchDTO.getSearchCondition() %>&keyword=<%=searchDTO.getKeyword()%>"><%=p %></a>
	    </li>
	    	<%} %>
	    <%} %>
	    <!-- next 페이지 -->
	    <%if(paging.isNext()){ %>
	    <li class="page-item">
	      <a class="page-link" href="boardList.do?page=<%=paging.getEndPage()+1%>&searchCondition=<%=searchDTO.getSearchCondition() %>&keyword=<%=searchDTO.getKeyword()%>" aria-label="Next">
	        <span aria-hidden="true">&raquo;</span>
	      </a>
	    </li>
	    <%} %>
	  </ul>
	</nav>
<%@ include file="../includes/footer.jsp" %>