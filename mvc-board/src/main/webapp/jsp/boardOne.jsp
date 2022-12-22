<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*" %>
<%
	// 1 요청분석
	request.setCharacterEncoding("utf-8");

	int no = Integer.parseInt(request.getParameter("no"));
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	// 2 요청처리
	Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/mvc","root","java1234");
		String sql = "SELECT * FROM board WHERE no = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, no);
		ResultSet rs = stmt.executeQuery();

	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>상세보기</h1>
	<table>
		<%
				while(rs.next()) { // ResultSet타입은 내부적 이터레이터 패턴이 적용된 API를 가지고 있다.
			%>
				<tr>
					<td><%=rs.getInt("no")%></td>
					<td>
						<a href='<%=request.getContextPath()%>/jsp/boardOne.jsp?no=<%=rs.getInt("no")%>'>
							<%=rs.getString("title")%>
						</a>
					</td>
				</tr>
			<%		
				}
			%>
	</table>
</body>
</html>