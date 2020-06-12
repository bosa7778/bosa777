<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시판 내용</title>
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css"  rel="stylesheet"/>
</head>

<body>
	<div class="container"> 
		글번호 : ${obj.brd_no}<br />
		글제목 : ${obj.brd_title}<br />
		글내용 : ${obj.brd_content}<br />
		작성자 : ${obj.brd_id}<br />
		조회수 : ${obj.brd_hit}<br />
		날짜 : ${obj.brd_date}<br />
		<img src="${pageContext.request.contextPath}/resources/img/default.jpg" width="100px" height="100px" /><br />
		<a href="/board/list" class="btn btn-success">목록</a>
	</div>
</body>
</html>