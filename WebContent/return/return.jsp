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
		<h3 align="left" style="padding-left:40px">検索条件</h3>


		<table border="1" cellpadding="2" cellspace="0"
			 style="margin-bottom:20px; width:95%; text-align:center">
			<tr style="background:#000088; color:white">
				<td>会員ID</td>
				<td>資料ID</td>
				<td>返却日</td>
				<td>返却期限</td>
				<td>返却</td>
			</tr>

			<c:forEach items="${rentalList}" var="detail">
			<tr style="background:white">
				<td>${detail.member_Id }</td>
				<td>${detail.detail_Id }</td>
				<td>${detail.rental_date}</td>
				<td>${detail.rental_due_date }</td>
				<td><a href="/I-TLibraryMng/ReturnServlet?action=${detail.member_Id}"> 返却 </a></td>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>