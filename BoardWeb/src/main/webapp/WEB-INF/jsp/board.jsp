<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix = "c" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<style>
	div.reply ul{
		list-style-type: none;
	}
	div.reply span{
		display: inline-block;
	}
</style>
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
        		<td colspan="4" align="center">
            		<input class="btn btn-danger" type="submit" value="삭제">
            		<button class="btn btn-warning" type="button" >수정</button>
            	</td>
       		</c:when>
       		<c:otherwise>
       			<td colspan="4" align="center">
            		<input class="btn btn-danger" disabled type="submit" value="삭제">
            		<button class="btn btn-warning" disabled type="button" >수정</button>
            	</td>
       		</c:otherwise>
       	</c:choose>
       </tr>
   </table>
</form>
<!-- 댓글 관련 -->
<div class="container reply">
	<!-- 등록 -->
	<div class="header">
		<input class="col-sm-8" id="content"></input>
		<button class="col-sm-3" id="addReply">댓글등록</button>
	</div>
	<!-- 목록 -->
	<div class="content">
		<ul id="replyList">
			<!-- 탬플릿 용 -->
			<li style="display: none;">
				<span class="col-sm-2">12</span>
				<span class="col-sm-5">댓글내용입니다.</span>
				<span class="col-sm-2">user02</span>
				<span class="col-sm-2"><button>삭제</button></span> 
			</li>
		</ul>
	</div>
	<!-- 댓글 페이징 -->
	<div class = "footer">
		<nav aria-label="...">
		  <ul class="pagination">
		    <!-- 자바스크립트로 채울것이다. -->
		  </ul>
		</nav>
	</div>
</div>
<script>
	// js/board.js 외부 스크립트 파일이 변수를 읽기위해 작성
	const bno = "${board.boardNo}";
	const replyer = "${logid}";
	document.querySelector('form>table button.btn.btn-warning')
		.addEventListener('click', function(e){
			location.href = 'modifyBoard.do?boardNo=${board.boardNo}';
		});
</script>
<!--<script src="js/boardService.js"></script>-->
<script src = "js/jqueryStudy/boardJquery.js"></script>
<script src="js/board.js" ></script>