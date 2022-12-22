<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*" %>
<%

	request.setCharacterEncoding("utf-8");
	//안전장치
	if(request.getParameter("title")==null || request.getParameter("title").equals("")
	|| request.getParameter("content")==null || request.getParameter("content").equals("")){
		response.sendRedirect(request.getContextPath()+"/addBoardForm.jsp");
		return;
	}
	
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	Class.forName("org.mariadb.jdbc.Driver");
	Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/mvc","root","java1234");
	String sql = "INSERT INTO board(title, content) VALUES(?,?)";
	PreparedStatement stmt = conn.prepareStatement(sql);
	stmt.setString(1, title);
	stmt.setString(2, content);
	
	int row = stmt.executeUpdate(); 
	
	if(row==1){
		response.sendRedirect(request.getContextPath()+"/jsp/jspBoardList.jsp");
		System.out.println("보드작성 성공");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>