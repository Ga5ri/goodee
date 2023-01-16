<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 공지사항 상세보기 -->
	<h1>공지 세부 내용</h1>
	<input type="hidden" name="noticeCode">
	<c:forEach var="n" items="${list}">
		<table border="1">
			<tr>
				<td>제목</td>
				<td>${n.noticeTitle}</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>${n.noticeContent}</td>
			</tr>
		</table>
		<!-- 관리자 이상만 진입 가능 -->
		<c:if test="${loginEmp != null}">
			<div>
				<button type="button" onclick="location.href='${pageContext.request.contextPath}/notice/modifyNotice?noticeCode=${n.noticeCode}'">수정</button>
				<button type="button" onclick="location.href='${pageContext.request.contextPath}/notice/deleteNotice?noticeCode=${n.noticeCode}'">삭제</button>
			</div>
		</c:if>
	</c:forEach>
</body>
</html>