<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardDelete</title>
</head>
<body>

	<div align="center" style="padding-top: 100px">
		<form method="post" action="boardDelete">
			<h1>게시글 삭제</h1>
			<br>
			<table border="1" style="width: 700px">
				<tr>
					<td>작성자</td>
					<td>${boardAdvanceDto.writer}</td>
				</tr>
				<tr >
					<td>작성일</td>
					<td>${boardAdvanceDto.regDate}</td>
				</tr>
				<tr >
					<td>제목</td>
					<td>${boardAdvanceDto.subject}</td>
				</tr>
				<tr >
					<td>패스워드</td>
					<td><input type="password" name="password" size="60"></td>
				</tr>
				<tr align="right">
					<td colspan="4">
						<input type="hidden" name="num" value="${boardAdvanceDto.num}">
						<input type="submit" value="글삭제">
						<input type="button" value="목록보기" onclick="location.href='boardList'">
						</td>
				</tr>
			</table>
		</form>
	</div>
	
</body>
</html>