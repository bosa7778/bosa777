<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원목록</title>
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" 
		rel="stylesheet"/>
</head>

<body>
	<div class="container"> 
		<h4>회원목록</h4>
		
		<table class="table">
			<thead>
				<tr>	
					<th>아이디</th>
					<th>이름</th>
					<th>연락처</th>
					<th>나이</th>
					<th>가입일자</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="tmp" items="${list}">
				<tr>
					<td>${tmp.userid}</td>
					<td>${tmp.username}</td>
					<td>${tmp.phone}</td>
					<td>${tmp.userage}</td>
					<td>${tmp.joindate}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>