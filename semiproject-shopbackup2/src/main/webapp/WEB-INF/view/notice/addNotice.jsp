<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 간이 메뉴바 -->
	<div>
		<jsp:include page = "/WEB-INF/view/inc/menu.jsp"></jsp:include>
	</div>
	
	<!-- 공지사항 입력 -->
	<h1>공지 입력</h1>
	<form action="${pageContext.request.contextPath}/notice/addNotice" method="post">
		<table border="1">
			<tr>
				<td>작성자 아이디</td>
				<td><input type="text" name="empId" value="${loginEmp.empId}" readonly="readonly"></td>
			</tr>
			<tr>
				<td>공지 제목</td>
				<td><input type="text" name="noticeTitle"></td>
			</tr>
			<tr>
				<td>공지 내용</td>
				<td><textarea rows="5" cols="30" name="noticeContent" placeholder="공지 내용을 입력하세요."></textarea></td>
			</tr>
		</table>
		<button type="submit">공지 추가</button>
	</form>
</body>
</html>