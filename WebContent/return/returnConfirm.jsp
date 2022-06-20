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
		<a href="/I-TLibraryMng/ReturnBtnServlet?action=done&id=${rental_due_date.id}&member_Id=0&detail_Id=0">返却</a>
		<a href="/I-TLibraryMng/ReturnServlet?action=returns">戻る</a>
	</div>
</table>
</body>
</html>