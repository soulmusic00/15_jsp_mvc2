<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardList</title>
<style>
	ul {
	    list-style:none;
	    text-align: center;
	}
	
	li {
	    display: inline-block;
	}
</style>
<script>

	function showList() {
		var onePageViewCount = document.getElementById("onePageViewCount").value; 
		var searchKeyword    = document.getElementById("searchKeyword").value;
		var searchWord       = document.getElementById("searchWord").value;
		
		var url = "boardList?";
		    url += "searchKeyword=" + searchKeyword;
		    url += "&searchWord=" + searchWord;
		    url += "&onePageViewCount=" + onePageViewCount;
		    
		location.href = url;
	}
	
</script>
</head>
<body>

	<p align="right">
		<input type="button" value="테스트 데이터 생성" onclick="location.href='boardMakeDummyData'">
	</p>

	<div align="center">
		<h2> 전체 게시글 보기 </h2>
		<br>
		<table border="1">
			<colgroup>
				<col width="10%">
				<col width="40%">
				<col width="20%">
				<col width="20%">
				<col width="10%">
			</colgroup>
			<tr>
				<td> 
					조회 : <span style="color:red">${allBoardCount}</span>개
				</td>
				<td colspan="4" align="right" >
					<select id="onePageViewCount" onchange="showList()" >
						<option value="5" <c:if test="${onePageViewCount == 5 }">selected</c:if> >5</option>
						<option value="7" <c:if test="${onePageViewCount == 7 }">selected</c:if> >7</option>
						<option value="10" <c:if test="${onePageViewCount == 10 }">selected</c:if> >10</option>
					</select>
				</td>
			</tr>
			<tr align="center">
				<td>번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>작성일</td>
				<td>조회수</td>
			</tr>
			<c:forEach var="boardAdvanceDto" items="${boardList }">
				<tr align="center">
					<td>${boardAdvanceDto.num }</td>
					<td align="left">
						<c:forEach var="i" begin="0" end="${boardAdvanceDto.reLevel }">
							<c:if test="${i > 1 }">
								&emsp;&emsp;
							</c:if>
						</c:forEach>						
						<c:if test="${boardAdvanceDto.reLevel != 1 }">
							>
						</c:if>
						<a href="boardInfo?num=${boardAdvanceDto.num}">${boardAdvanceDto.subject}</a>
					</td>
					<td>${boardAdvanceDto.writer }</td>
					<td>${boardAdvanceDto.regDate }</td>
					<td>${boardAdvanceDto.readCount }</td>
				</tr>
			</c:forEach>
			<tr align="right">
				<td colspan="5">
					<input type="button" style="float: right" value="글쓰기" onclick="location.href='boardWrite'">
				</td>
			</tr>
			<tr>
				<td colspan="5" align="center">			
					<select id="searchKeyword">
						<option value="total" <c:if test="${searchKeyword eq 'total' }"> selected</c:if> >전체검색</option>
						<option value="writer" <c:if test="${searchKeyword eq 'writer' }"> selected</c:if> >작성자</option>
						<option value="subject" <c:if test="${searchKeyword eq 'subject' }"> selected</c:if> >제목</option>
					</select>
					<input type="text" id="searchWord" name="searchWord" value="${searchWord}"> 
					<input type="button" value="검색" onclick="showList()">
				</td>
			</tr>
		</table>
	</div>
	<div style="align-items: center">
		<ul>
			<c:forEach var="i" begin="${startPage }" end="${endPage}">
				<c:if test="${currentPage <= allPageCount}">
					<c:if test="${i > 10 and i % 10 == 1}">
						<li><a href="boardList?currentPage=${startPage - 10 }&onePageViewCount=${onePageViewCount}&searchKeyword=${searchKeyword}&searchWord=${searchWord}">이전</a></li>
					</c:if>
					<li><a href="boardList?currentPage=${i }&onePageViewCount=${onePageViewCount}&searchKeyword=${searchKeyword}&searchWord=${searchWord}">${i }</a> &nbsp;</li>
					<c:if test="${i % 10 == 0 and i < allPageCount}">
						<li><a href="boardList?currentPage=${startPage + 10 }&onePageViewCount=${onePageViewCount}&searchKeyword=${searchKeyword}&searchWord=${searchWord}">다음</a></li>
					</c:if>
				</c:if>
			</c:forEach>
		</ul>
	</div>
	
</body>
</html>