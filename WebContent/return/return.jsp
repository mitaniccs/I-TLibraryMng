<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>資料返却画面</title>

</head>
<body>
	<div align="center" style="background:#cccccc">
		<h2>資料返却画面</h2>


		<table border="1" cellpadding="2" cellspace="0"
			width="600" style="margin-bottom:20px;">
			<tr style="background:blue">
				<td>会員ID</td>
				<td>資料ID</td>
				<td>返却日</td>
				<td>返却期限</td>
				<td>返却日</td>
			</tr>

			<c:forEach items="${rentalList}" var="detail">
			<tr>
				<td>${detail.member_Id }</td>
				<td>${detail.detail_Id }</td>
				<td>${detail.rental_date}</td>
				<td>${detail.rental_due_date }</td>
				<td>${detail.returned_date}</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>