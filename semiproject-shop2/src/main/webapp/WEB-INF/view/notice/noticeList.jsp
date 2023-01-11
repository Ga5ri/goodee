<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>공지 사항</h1>
	<table border="1">
		<tr>
			<th>공지번호</th>
			<th>공지 제목</th>
			<th>날짜</th>
		</tr>
		<c:forEach var="n" items="${list}">
			<tr>
				<td>${n.noticeCode}</td>
				<td><a href="${pageContext.request.contextPath}/notice/noticeOne?noticeCode=${n.noticeCode}">${n.noticeTitle}</a></td>
				<td>${n.createdate}</td>
			</tr>
		</c:forEach>
	</table>
	<!-- 페이징 -->
	<div>
		<a href="${pageContext.request.contextPath}/notice/noticeList?currentPage=1">처음</a>		
		<c:if test="${currentPage > 1}">
			<a href="${pageContext.request.contextPath}/notice/noticeList?currentPage=${currentPage-1}">이전</a>
		</c:if>	
		<span>${currentPage}</span>	
		<c:if test="${currentPage < lastPage}">
			<a href="${pageContext.request.contextPath}/notice/noticeList?currentPage=${currentPage+1}">다음</a>
		</c:if>	
		<a href="${pageContext.request.contextPath}/notice/noticeList?currentPage=${lastPage}">마지막</a>
	</div>
</body>
</html>