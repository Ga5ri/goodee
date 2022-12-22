<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jspBoardList</title>
</head>
<body>
	<h1>JSP BOARD LIST</h1>
	<%
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/mvc","root","java1234");
		String sql = "SELECT no, title, content FROM board";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
	%>
	<div>
		<a href="<%=request.getContextPath()%>/jsp/addBoardForm.jsp">글입력</a>
	</div>
	<table border="1">
		<thead>
			<tr>
				<th>no</th>
				<th>title</th>
			</tr>	
		</thead>
		<tbody>
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
		</tbody>
	</table>
</body>
</html>