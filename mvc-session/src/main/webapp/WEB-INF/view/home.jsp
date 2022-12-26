<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>${loginMember.getMemberName()}님 반갑습니다.</div>
	<div>
		<a href="${pageContext.request.contextPath}/LogoutController">로그아웃</a>
	</div>
</body>
</html>