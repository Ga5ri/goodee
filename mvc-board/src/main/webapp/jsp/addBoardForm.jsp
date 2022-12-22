<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>글 입력</h1>
	<form action="<%=request.getContextPath()%>/jsp/addBoardAction.jsp" method="post">
		<table border="1">
			<tr>
				<td>제목</td>
				<td><input type="text" name="title" value=""></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="3" cols="30" name="content"></textarea></td>
			</tr>
		</table>
		<div>
			<button type="submit">작성하기</button>
		</div>
	</form>
</body>
</html>