<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06_bUpdate</title>
</head>
<body>
	<div align="center" style="padding-top: 100px">
		<form action="bUpdateAction" method="post" >
			<div align="center">
				<h1>게시글 수정</h1>
				<br>
			</div>
			<table style="width: 700px;" border="1">
				<tr>
					<td>작성자</td>
					<td>${memberDto.writer}</td>
				</tr>
				<tr>
					<td>작성일</td>
					<td>${memberDto.regDate}</td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="subject" value="${memberDto.subject}" /></td>
				</tr>
				<tr>
					<td>패스워드</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td>글내용</td>
					<td><textarea rows="10" cols="60" name="content">${memberDto.content}"</textarea></td>
				</tr>
				<tr align="center">
					<td colspan="4">
						<input type="hidden" name="num" value="${memberDto.num }" /> 
						<input type="submit" value="글수정" />
						<input type="button" onclick="location.href='bList'" value="전체글보기" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>