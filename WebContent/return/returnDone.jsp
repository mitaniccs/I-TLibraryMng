<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>資料返却完了画面</title>
</head>
<body>
<table >
	<div align="center" style="background:#cccccc">
		<h1>資料返却完了画面</h1>
		<p style="font-weight:bolder">名前<span></span></p>
		<P style="font-weight:bolder">資料名<span></span></P>
		<p style="font-weight:bolder">返却期限<span></span></p>
		<br><br><br>
		<form>
   			 <a class="btn" href="return/return.jsp">戻る</a>
		</form>
	</div>
</table>
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