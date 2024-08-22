<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix = "c" %>
<link href="https://cdn.datatables.net/2.1.4/css/dataTables.dataTables.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://cdn.datatables.net/2.1.4/js/dataTables.js"></script>

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
	<table id="example" class="display" style="width:100%">
        <thead>
            <tr>
                <th>댓글번호</th>
                <th>댓글 내용</th>
                <th>작성자</th>
                <th>작성일시</th>
            </tr>
        </thead>
        <tfoot>
            <tr>
                <th>댓글번호</th>
                <th>댓글 내용</th>
                <th>작성자</th>
                <th>작성일시</th>
            </tr>
        </tfoot>
    </table>
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
<!-- AJAX 사용을 위한 자바스크립트 -->
<script>
$('#example').DataTable({
    ajax: 'replyList.do?bno='+ bno, // http 요청을 보낸다.
    columns: [ // 반환된 값을 아래방식으로 받아준다.
        { data: 'replyNo' },
        { data: 'replyContent' },
        { data: 'replyer' },
        { data: 'replyDate' },
    ],
    // 페이지 길이 옵션
    lengthMenu: [
        [5, 10, 20, -1],
        [5, 10, 20, 'All']
    ]
});
</script>