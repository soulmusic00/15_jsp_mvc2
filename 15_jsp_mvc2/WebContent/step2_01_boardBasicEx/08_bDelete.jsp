<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08_bDelete</title>
</head>
<body>
	<div align="center" style="padding-top: 100px">
		<form action="bDeleteAction" method="post">
			<div align="center">
				<h1>게시글 삭제</h1>
				<br>
			</div>
			<table style="width: 700px" border="1">
				<tr>
					<td>작성자</td>
					<td>${boardDto.writer}</td>
				</tr>
				<tr>
					<td>작성일</td>
					<td>${boardDto.regDate}</td>
				</tr>
				<tr>
					<td>제목</td>
					<td>${boardDto.subject}</td>
				</tr>
				<tr>
					<td>패스워드</td>
					<td><input type="password" name="password" size="60"></td>
				</tr>
				<tr align="right">
					<td colspan="4">
						<input type="hidden" name="num" value="${boardDto.num}">
						<input type="submit" value="글삭제">
						<input type="button" onclick="location.href='bList'" value="목록보기">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>