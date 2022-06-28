<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>資料返却確認画面</title>
<style>span{margin-right: 5px;}</style>
<link rel=”stylesheet” type=”text/css” href=”return.css”>
</head>
<body>
<table >
	<div align="center" style="background:#cccccc">
		<h1>資料返却確認画面</h1>
		<p style="font-weight:bolder">名前${name.name }</p>
		<P style="font-weight:bolder">資料名<span>${title.title }</span></P>
		<p style="font-weight:bolder">返却期限<span>${rental_due_date.rental_due_date}</span></p>
		<!--  <input type="submit" name="action" value="返却">
		<input type="submit" name="action" value="戻る">-->
		<!-- <a href="/I-TLibraryMng/ReturnBtnServlet?action=done&id=${rental_due_date.id}&member_Id=0&detail_Id=0">返却</a>
		 -->
		<form action="./ReturnBtnServlet" method="POST">
			<input type="hidden" value="done" name="action">
			<input type="hidden" value="${rental_due_date.id}" name="id">
			<input type="hidden" value="0" name="detail_Id">
			<input type="hidden" value="0" name="member_Id">
			<input type="submit" value="返却">
		</form>
		<form action="./ReturnServlet" method="POST">
			<input type="hidden" value="returnbtnconfirm" name="action">
   			<input type="submit" value="戻る">
		</form>
	</div>
</table>
</body>
</html>