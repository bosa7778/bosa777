<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>물품목록</title>
	<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" 
		rel="stylesheet"/>
</head>

<body>

	<div class="container"> 
		
		<form action="/admin/item" method="post">
		<a href="/admin/iteminsert" class="btn btn-success">일괄추가</a>
		<input type="submit" name="btn" class="btn btn-success" value="일괄수정" />
		<input type="submit" name="btn" class="btn btn-success" value="일괄삭제" />
		
		<table class="table table-sm">
			<thead>
				<tr>	
					<th><input type="checkbox" /></th>
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
					<td><input type="checkbox" name="chk[]" value="${tmp.itemno}" /></td>
					<td>${tmp.itemno}</td>
					<td>${tmp.itemname}</td>
					<td>${tmp.itemprice}</td>
					<td>${tmp.itemqty}</td>
					<td>${tmp.itemdes}</td>
					<td>${tmp.itemdate}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		</form>

	</div>
</body>
</html>