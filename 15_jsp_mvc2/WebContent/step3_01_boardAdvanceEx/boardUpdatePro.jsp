<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07_updatePro</title>
</head>
<body>
	<c:choose>
		<c:when test="${isUpdate eq true }">
			<script>
				alert('수정되었습니다.');
				location.href = "boardList";
			</script>
		</c:when>
		<c:otherwise>
			<script>
				alert('패스워드가 일치하지 않습니다. 다시 확인 후 수정해주세요.');
				history.go(-1);
			</script>
		</c:otherwise>
	</c:choose>
</body>
</html>