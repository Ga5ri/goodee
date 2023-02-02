<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>EMP 회원가입</title>
		
		<!-- jQuery -->
		<!-- CDN 주소 추가 방식 -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		
		<!-- Bootstrap CDN 시작 -->
		<!-- Latest compiled and minified CSS -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
		
		<!-- Latest compiled JavaScript -->
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
		<!-- Bootstrap CDN 끝 -->
		
	</head>
	
	
	<body>
		<div>
			<div>
				<h1>EMP 회원가입</h1>
			</div>
		
			<div>
				<form action = "${pageContext.request.contextPath }/emp/addEmp" method = "post">
					<div>
						<table border = "1">
							<tr>
								<th>empId</th>
								<td>
									<input type = "text" name = "empId" id = "empId">
								</td>
							</tr>
							
							<tr>
								<th>empPw</th>
								<td>
									<input type = "password" name = "empPw" id = "empPw">
								</td>
							</tr>
							
							<tr>
								<th>empName</th>
								<td>
									<input type = "text" name = "empName" id = "empName">
								</td>
							</tr>
						</table>
					</div>
				
					<div>
						<button type = "submit">회원가입</button>
					</div>
				
				</form>
			</div>
		
		</div>
		
	</body>
</html>