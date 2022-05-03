<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>01_main</title>
</head>
<body>

	<%--
	
		이클립스에서 Servers폴더에 있는 Context.xml파일에 아래의 내용 수정 
	
		<Resource 
			auth="Container" 
			driverClassName="com.mysql.cj.jdbc.Driver"
			loginTimeout="10" 
			maxWait="5000" 
			name="jdbc/pool" 
			username="root"
			password="1234" 
			type="javax.sql.DataSource"
			url="jdbc:mysql://localhost:3306/MVC2_BOARD_BASIC_EX?serverTimezone=UTC"
		/> 
	
	 --%>

	<div align="center" style="padding-top: 100px">
		<img src="img/jsp.PNG" alt="jsp심볼" width="800px" height="500px"><br><br><br><br>
		<input type="button" value="게시판 보기" onclick="location.href='bList'">
	</div>
</body>
</html>