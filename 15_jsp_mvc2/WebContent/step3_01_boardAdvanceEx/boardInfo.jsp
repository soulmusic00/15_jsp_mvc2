<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardInfo</title>
</head>
</head>
<body>
	<div align="center">
		<h1>게시글 보기</h1>
		<br>
		<table border="1">
			<tr>
				<td>글번호</td>
				<td>${boardAdvanceDto.num}</td>
			</tr>
			<tr>
				<td>조회수</td>
				<td>${boardAdvanceDto.readCount}</td>
			</tr>
			<tr >
				<td>작성자</td>
				<td>${boardAdvanceDto.writer}</td>
			</tr>
			<tr>
				<td>작성일</td>
				<td>${boardAdvanceDto.regDate}</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td>${boardAdvanceDto.email}</td>
			</tr>
			<tr>
				<td>제목</td>
				<td>${boardAdvanceDto.subject}</td>
			</tr>
			<tr>
				<td>글 내용</td>
				<td>${boardAdvanceDto.content}</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" value="답글쓰기" onclick="location.href='boardRe?num=${boardAdvanceDto.num}'"> 
					<input type="button" value="수정하기" onclick="location.href='boardUpdate?num=${boardAdvanceDto.num}'">
					<input type="button" value="삭제하기" onclick="location.href='boardDelete?num=${boardAdvanceDto.num}'">
					<input type="button" value="목록보기" onclick="location.href='boardList'">
				</td>
			</tr>
		</table>
	</div>
</body>
</html>