<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>empOne.jsp</title>
		
		<!-- jQuery -->
		<!-- CDN 주소 추가 방식 -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		
	</head>
	
	
	<body>
		<div>
			<div>
				<h1>EMP ONE</h1>
			</div>
			
			<div>
				<table border = "1">
					<tr>
						<th>EmpCode</th>
						<td>
							<input type = "text" name = "empCode" id = "empCode" value = "${emp.empCode }" readonly = "readonly">
						</td>
					</tr>
					
					<tr>
						<th>EmpId</th>
						<td>
							<input type = "text" name = "empId" id = "empId" value = "${emp.empId }" readonly = "readonly">
						</td>
					</tr>
					
					<tr>
						<th>empName</th>
						<td>
							<input type = "text" name = "empName" value = "${emp.empName }" readonly = "readonly">
						</td>
					</tr>
					
					<tr>
						<th>active</th>
						<td>
							<input type = "text" name = "active" value = "${emp.active }" readonly = "readonly">
						</td>
					</tr>
					
					<tr>
						<th>auth_code</th>
						<td>
							<input type = "text" name = "auth_code" value = "${emp.authCode }" readonly = "readonly">
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
				<button type = "button" 
					onClick="location.href='${pageContext.request.contextPath }/emp/modifyEmpByAdmin?empId=${emp.empId }'">
						수정
				</button>
				<button type = "button" 
					onClick="location.href='${pageContext.request.contextPath }/emp/deleteEmp?empId=${emp.empId }'">
						삭제
				</button>
			</div>
		</div>		
	</body>
</html>