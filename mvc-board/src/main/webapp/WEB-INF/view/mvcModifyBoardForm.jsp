<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "service.*" %>
<%@ page import = "vo.*" %>
<%
    Board board = (Board)request.getAttribute("board");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mvcModifyBoardForm</title>
</head>
<body>
	<h1>Modify Board</h1>
	<div>
		<a href="<%=request.getContextPath()%>/BoardListController">홈으로</a>
	</div>
	<form action="<%=request.getContextPath()%>/ModifyBoardActionController" method="post">
		<table border="1">
			<tr>
				<th>title</th>
				<td>
					<input type="hidden" name="no" value="<%=board.getNo()%>">
					<input type="text" name="title" value="<%=board.getTitle()%>">
				</td>
			</tr>
			<tr>
				<th>content</th>
				<td>
					<textarea rows="5" cols="50" name="content"><%=board.getContent()%></textarea>
				</td>
			</tr>
		</table>
		<button type="submit">수정</button>
	</form>
</body>
</html>
​