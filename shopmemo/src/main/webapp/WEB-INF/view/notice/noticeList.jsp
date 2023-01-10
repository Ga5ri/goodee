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
	<table>
		<tr>
			<td>공지번호</td>
			<td>공지 제목</td>
			<td>공지 내용</td>
		</tr>
		<tr>
			<c:forEach var="n" items="${noticeList}">
				<td>${n.noticeCode}</td>
				<td>${n.noticeTitle}</td>
				<td>${n.noticeContent}</td>
			</c:forEach>
		</tr>
	</table>
</body>
</html>