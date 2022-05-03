<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02_bWrite</title>
<script>

	function formValidationCheck() {
		
		var writer = document.f.writer;
		if (writer.value.length == 0){
			alert("작성자를 입력하세요.");
			writer.focus();
			return false;
		}
		
		var subject = document.f.subject;
		if (subject.value.length == 0){
			alert("제목을 입력하세요.");
			subject.focus();
			return false;
		}
		
		var password = document.f.password;
		if (password.value.length == 0){
			alert("비밀번호를 입력하세요.");
			password.focus();
			return false;
		}
		
		var email = document.f.email;
		if (email.value.length == 0){
			alert("이메일을 입력하세요.");
			email.focus();
			return false;
		}	
		
		return true;
		
	}
</script>
</head>
<body>
	<div align="center" style="padding-top: 100px">
		<form action="bWriteAction" name="f" method="post" onsubmit="return formValidationCheck()">
			<div  align="center">
				<h2>게시글 쓰기</h2>
				<br>
			</div>
			<table style="width: 700px;" border="1">
				<colgroup>
					<col width="20%">
					<col width="80%">
				</colgroup>
				<tr>
					<td align="center"><span style="color: red">*</span> 작성자</td>
					<td><input type="text" id="writer" name="writer" size="80"/></td>
				</tr>
				<tr >
					<td align="center"><span style="color: red">*</span> 제목</td>
					<td><input type="text" id="subject" name="subject" size="80"/></td>
				</tr>
				<tr>
					<td align="center"><span style="color: red">*</span> 이메일</td>
					<td><input type="email" id="email" name="email" size="80"/></td>
				</tr>
				<tr>
					<td align="center"><span style="color: red">*</span> 비밀번호</td>
					<td><input type="password" id="password" name="password" size="80"/></td>
				</tr>
				<tr>
					<td align="center">글내용</td>
					<td><textarea  rows="10" cols="50" id="content" name="content" ></textarea></td>
				</tr>
				<tr align="center">
					<td colspan="2">
						<input type="submit" value="글쓰기" />
						<input type="reset"  value="다시작성" />
						<input type="button" onclick="location.href='bList'" value="전체게시글보기">
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>