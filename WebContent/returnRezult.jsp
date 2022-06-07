<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>資料返却履歴</title>
</head>

<body>
	<div align="center">
		<h2>資料返却履歴</h2>
			<table border="1" cellpadding="2" cellspace="0"
				width="600" style="margin-bottom:20px;">
				<tr>
					<td>会員ID</td>
					<td>${ReturnBean.member_Id }</td>
				</tr>
				<tr>
					<td>資料ID</td>
					<td>${ReturnBean.detail_Id }"</td>
				</tr>
				<tr>
					<td>返却日</td>
					<td>${ReturnBean.rental_date}</td>
				</tr>
				<tr>
					<td>返却期限</td>
					<td>${ReturnBean.rental_due_date }"</td>
				</tr>
				<tr>
					<td>返却日</td>
					<td>${ReturnBean.returned_date}"</td>
				</tr>
			</table>
	</div>
</body>
</html>