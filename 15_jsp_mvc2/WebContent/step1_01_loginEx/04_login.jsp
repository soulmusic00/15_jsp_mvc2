<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04_login</title>
</head>
<body>
	<div align="center">
		<h1>로그인</h1>
		<p>입사지원을 위해서는 로그인이 필요합니다.</p>
		<hr>
		<form action="login" method="post">
			<p><label>아이디 :   <input type="text"     name="id" autofocus></label></p>
			<p><label>패스워드 : <input type="password" name="pw"></label></p>
			<input type="submit" value="로그인">
		</form>
	</div>
</body>
</html>