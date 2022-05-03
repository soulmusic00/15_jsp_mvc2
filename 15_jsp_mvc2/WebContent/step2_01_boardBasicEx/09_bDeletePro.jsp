<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09_bDeletePro</title>
</head>
<body>
	<c:choose>
		<c:when test="${isDelete eq true}">
			<script>
				alert('삭제되었습니다.');
				location.href="bList";
			</script>
		</c:when>
		<c:otherwise>
			<script>
				alert('패스워드가 일치하지 않습니다. 다시 확인 후 삭제해주세요.');
				history.go(-1);
			</script>
		</c:otherwise>
	</c:choose>
</body>
</html>