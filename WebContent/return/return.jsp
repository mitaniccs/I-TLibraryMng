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
		<div align="left" style="padding-left:40px">
			<h3>検索条件</h3>
			<form action="I-TLibraryMng/SearchServlet" method="POST">
				<p>会員ID：<input type="search" name="memberID"></p>
				<p>資料ID：<input type="search" name="articleID">
					<input type="submit" value="検索"></p>
			</form>
		</div>
		<table border="1" cellpadding="2" cellspace="0"
			 style="margin-bottom:20px; width:95%; text-align:center">
			<tr style="background:#000088; color:white">
				<td>会員ID</td>
				<td>資料ID</td>
				<td>返却日</td>
				<td>返却期限</td>
				<td>返却</td>
			</tr>

			<c:forEach items="${returnList}" var="detail">
			<tr style="background:white">
				<td>${detail.member_Id }</td>
				<td>${detail.detail_Id }</td>
				<td>${detail.rental_date}</td>
				<td>${detail.rental_due_date }</td>
				<td>
					<!--URLパラメータに2値入れられなかった。そもそもセッション保管されたIdを使うべき
					<a class="btn" href="/onelibrary2/ReturnServlet?action=confirm?detail_Id=${detail.detail_Id}?member_Id=${detail.member_Id}">-->
					<a class="btn" href="/I-TLibraryMng/ReturnBtnServlet?action=confirm&id=${detail.id}&detail_Id=${detail.detail_Id}&member_Id=${detail.member_Id}">返却 </a>
				</td>
			</tr>
			</c:forEach>
		</table>
		<form>
   			 <a class="btn" href="return/test.jsp">戻る</a>
		</form>
	</div>
</body>
</html>

<style>
	.btn {
 display       : inline-block;
 border-radius : 5%;          /* 角丸       */
 font-size     : 13pt;        /* 文字サイズ */
 text-align    : center;      /* 文字位置   */
 cursor        : pointer;     /* カーソル   */
 padding       : 2px 5px;   /* 余白       */
 background    : #999999;     /* 背景色     */
 color         : #ffffff;     /* 文字色     */
 line-height   : 1em;         /* 1行の高さ  */
 transition    : .3s;         /* なめらか変化 */
 border        : 2px solid #999999;    /* 枠の指定 */
	}
	.btn:hover {
	  box-shadow    : none;        /* カーソル時の影消去 */
	  color         : #999999;     /* 背景色     */
	  background    : #ffffff;     /* 文字色     */
	}

</style>