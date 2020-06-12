<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>물품일괄수정</title>
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css"	rel="stylesheet"/>
</head>

<body>
	<div class="container">
		<form action="/admin/itemupdate" method="post">
		<input type="submit" class="btn btn-success" value="일괄수정" />
		<table class="table table-sm">
			<thead>
				<tr>	
					<th>번호</th>
					<th>품명</th>
					<th>가격</th>
					<th>재고수량</th>
					<th>내용</th>
					<th>날짜</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="tmp" items="${list}">
				<tr>
					<td>${tmp.itemno}<input type="hidden" name="no[]" value="${tmp.itemno}" /></td>
					<td><input type="text" name="name[]" value="${tmp.itemname}" /></td>
					<td><input type="text" name="price[]" value="${tmp.itemprice}" /></td>
					<td><input type="text" name="qty[]" value="${tmp.itemqty}" /></td>
					<td><input type="text" name="des[]"value="${tmp.itemdes}" /></td>
					<td>${tmp.itemdate}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		</form>
	</div>
</body>
</html>