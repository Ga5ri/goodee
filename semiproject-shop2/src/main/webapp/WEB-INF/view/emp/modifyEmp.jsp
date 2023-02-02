<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>modifyEmp.jsp</title>
		
		<!-- jQuery -->
		<!-- CDN 주소 추가 방식 -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		
	</head>
	
	
	<body>
		<div>
			<div>
				<h1>emp 수정</h1>
			</div>
			
			<div>
				<form action = "${pageContext.request.contextPath }/emp/modifyEmp" method = "post">
					<div>
						<table border = "1">
							<tr>
								<th>empId</th>
								<td>
									<input type = "text" name = "empId" id = "empId" value = "${emp.empId }" readonly = "readonly">
								</td>
							</tr>
							
							<tr>
								<th>empName</th>
								<td>
									<input type = "text" name = "empName" value = "${emp.empName }" id = "empName">
								</td>
							</tr>
							
							<tr>
								<th>createdate</th>
								<td>
									<input type = "text" name = "createdate" id = "createdate" value = "${emp.createdate }" readonly = "readonly">
								</td>
							</tr>
						</table>
					</div>
					
					<div>
						<button type = "submit">수정</button>
					</div>
				</form>
			</div>
		</div>		
	</body>
</html>