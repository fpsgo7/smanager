<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--  요기는 html/index.jsp -->
<h3>연습합시다.</h3>
<div id = "show">
</div>
<script src="js/basic.js"></script>

<div class="row">
    <table class="table">
        <tr>
            <th>이름</th>
            <td><input type="text" class="form-contol" id="fname"></td>
        </tr>
        <tr>
            <th>주소</th>
            <td><input type="text" class="form-contol" id="faddress"></td>
        </tr>
        <tr>
            <th>키</th>
            <td><input type="number" class="form-contol" id="height"></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <button id="addBtn" class="btn btn-primary">등록</button>
                <button id="modBtn" class="btn btn-warning">수정</button>
                <button id="delBtn" class="btn btn-danger">삭제</button>
            </td>
        </tr>
    </table>
</div>

<table class="table">
    <thead>
    	<tr>
	    	<th>친구이름</th>
	        <th>주소</th>
	        <th>키</th>
	        <th>삭제</th>
	        <th><input type="checkbox" name="all"></th>
    	</tr>
    </thead>
    <tbody id="list">
    </tbody>
</table>
<script src="js/array1.js"></script>

<div id ="show">
	<select class = "form-control" id="searchGender">
		<option value="All">선택하세요</option>
		<option value="Female">여성</option>
		<option value="Male">남성</option>		
	</select>
	<table class="table">
		<thead>
			<tr>
				<th>사원번호</th>
				<th>이름</th>
				<th>성</th>
				<th>월급</th>
			</tr>
		</thead>
		<tbody id = "empList">
			<!--  사원 번호 , fname, lname, salary -->
			
		</tbody>
	</table>
	
</div>

<div id="student">
	<table class="table">
	    <thead>
	    	<tr>
		    	<th>학생번호</th>
		        <th>학생이름</th>
		        <th>연락처</th>
		        <th>삭제</th>
	    	</tr>
	    </thead>
	    <tbody id="studentList">
	    	<!-- 자바스크립트로 그릴것이다. -->
	    </tbody>
	</table>
	<hr>
	<table class="table">
        <tr>
            <th>학생번호</th>
            <td><input type="text" class="form-contol" id="sno"></td>
        </tr>
        <tr>
            <th>이름</th>
            <td><input type="text" class="form-contol" id="sname"></td>
        </tr>
        <tr>
            <th>연락처</th>
            <td><input type="number" class="form-contol" id="phone"></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <button id="addStudentBtn" class="btn btn-primary">등록</button>
            </td>
        </tr>
    </table>
</div>

<script src="js/json3.js"></script>