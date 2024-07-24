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
            </td>
        </tr>
    </table>
</div>

<table class="table">
    <thead>
        <th>친구이름</th>
        <th>주소</th>
        <th>키</th>
        <th>삭제</th>
        <th><input type="checkbox" id="allCheckBox" name="all"></th>
    </thead>
    <tbody id="list">
    </tbody>
</table>

<script src="js/array1.js"></script>