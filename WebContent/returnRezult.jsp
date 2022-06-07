<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>資料返却履歴</title>
<link rel=”stylesheet” type=”text/css” href=”return.css”>
</head>

<body>
	<div align="center">
		<h2>資料返却履歴</h2>
		<table border="1" cellpadding="2" cellspace="0"
			width="600" style="margin-bottom:20px;">
			<tr>
				<td>会員ID</td>
				<td>資料ID</td>
				<td>返却日</td>
				<td>返却期限</td>
				<td>返却日</td>
			</tr>

			<c:forEach items="${resultList}" var="detail">
				<td>${detail.member_Id }</td>
				<td>${detail.detail_Id }"</td>
				<td>${detail.rental_date}</td>
				<td>${detail.rental_due_date }"</td>
				<td>${detail.returned_date}"</td>
			</c:forEach>
		</table>
	</div>
</body>
</html>