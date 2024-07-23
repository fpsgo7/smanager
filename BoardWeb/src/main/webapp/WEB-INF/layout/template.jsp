<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title><tiles:getAsString name="title"/></title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body>
        <div class="d-flex" id="wrapper">
			<tiles:insertAttribute name="menu"/> <!-- Sidebar 메뉴 부분-->
            <!-- Page content wrapper-->
            <div id="page-content-wrapper">
                <tiles:insertAttribute name="header"/> <!-- Top navigation  헤더 부분-->
                <!-- Page content-->
                <div class="container-fluid">
                    <tiles:insertAttribute name="body"/> <!-- 바디 부분 -->
                </div>
            </div>
        </div>
        <tiles:insertAttribute name="footer"/> <!-- 푸터 부분 -->
    </body>
</html>
