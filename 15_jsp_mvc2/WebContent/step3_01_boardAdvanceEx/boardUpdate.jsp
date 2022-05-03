<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06_bUpdate</title>
</head>
<body>
	<div align="center">
		<form action="boardUpdate" method="post">
			<h1>게시글 수정</h1>
			<br>
			<table border="1">
				<tr>
					<td>작성자</td>
					<td>${boardAdvanceDto.writer}</td>
				</tr>
				<tr>
					<td>작성일</td>
					<td>${boardAdvanceDto.regDate}</td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="subject" value="${boardAdvanceDto.subject}" /></td>
				</tr>
				<tr>
					<td>패스워드</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td>글내용</td>
					<td><textarea rows="10" cols="60" name="content">${boardAdvanceDto.content}</textarea></td>
				</tr>
				<tr>
					<td colspan="4">
						<input type="hidden" name="num" value="${boardAdvanceDto.num}" /> 
						<input type="submit" value="글수정" />
						<input type="button" onclick="location.href='boardList'" value="전체글보기" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
