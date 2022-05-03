<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%--

		# MVC2 데이터베이스 연동예시


		1. 이클립스에서 해당 프로젝트 > WebContent > WEB-INF > lib폴더에 아래의 라이브러리 추가 
			
			commons-collections4-4.1.jar
			commons-dbcp2-2.2.0.jar
			commons-pool2-2.5.0.jar
			jstl-1.2.jar
			mysql-connector-java-8.0.15.jar
			
		
		2. 이클립스에서 Servers폴더에 있는 Context.xml파일에 아래의 내용 추가 
		
			<Resource 
				auth="Container" 
				driverClassName="com.mysql.cj.jdbc.Driver"
				loginTimeout="10" 
				maxWait="5000" 
				name="jdbc/pool" 
				username="root"
				password="1234" 
				type="javax.sql.DataSource"
				url="jdbc:mysql://호스트명:포트번호/데이터베이스명?serverTimezone=UTC"
			/> 
 
 --%>

	<div align="center">
		<c:if test="${id ne null }">
			${id }님 환영합니다.<br><br>
			<p>입사지원정보 수정</p>
			<p><a href="logout">로그아웃</a></p>
			<p>회원탈퇴</p>
		</c:if>
		
		<c:if test="${id eq null}">
			<p><a href="join">회원가입</a></p>
			<p><a href="login">로그인</a></p>
		</c:if>
	</div>
	
	<hr><br><br><br>
	
	<div align="center">
		<a href="apply"><img src="img/applyonline.png" alt="입사지원하기"></a>
	</div>
		


</body>
</html>