<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 공지사항 수정 -->
	<h1>공지 수정</h1>
	<form action="${pageContext.request.contextPath}/notice/modifyNotice" method="post">
		<c:forEach var="n" items="${list}">
			<table border="1">
				<tr>
					<td>공지 번호</td>
					<td><input type="text" name="noticeCode" value="${n.noticeCode}" readonly="readonly"></td>
				</tr>
				<tr>
					<td>작성자 아이디</td>
					<td><input type="text" name="empId" value="${n.empId}" readonly="readonly"></td>
				</tr>
				<tr>
					<td>공지 제목</td>
					<td><input type="text" name="noticeTitle" value="${n.noticeTitle}"></td>
				</tr>
				<tr>
					<td>공지 내용</td>
					<td><textarea name="noticeContent">${n.noticeContent}</textarea></td>
				</tr>
			</table>
		</c:forEach>
		<div>
			<button type="submit">수정하기</button>
		</div>
	</form>
</body>
</html>