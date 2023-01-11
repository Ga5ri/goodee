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
	<h1>공지 세부 내용</h1>
	<input type="hidden" name="noticeCode">
	<table border="1">
		<c:forEach var="n" items="${list}">
			<tr>
				<td>제목</td>
				<td>${n.noticeTitle}</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>${n.noticeContent}</td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<button type="button" onclick="">수정</button>
		<button type="button" onclick="">삭제</button>
	</div>
</body>
</html>