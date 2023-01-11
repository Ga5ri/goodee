<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원가입</title>
		
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
				<h1>CUSTOMER 회원가입</h1>
			</div>
		
			<div>
				<form action = "${pageContext.request.contextPath }/customer/addCustomer" method = "post">
					<div>
						<table border = "1">
							<tr>
								<th>customerId</th>
								<td>
									<input type = "text" name = "customerId" id = "customerId">
								</td>
							</tr>
							
							<tr>
								<th>customerPw</th>
								<td>
									<input type = "password" name = "customerPw" id = "customerPw">
								</td>
							</tr>
							
							<tr>
								<th>customerName</th>
								<td>
									<input type = "text" name = "customerName" id = "customerName">
								</td>
							</tr>
							
							<tr>
								<th>customerPhone</th>
								<td>
									<input type = "text" name = "customerPhone" id = "customerPhone">
								</td>
							</tr>
							
							<tr>
								<th>address</th>
								<td>
									<textarea rows = "3" cols = "50" name = "address" id = "address"></textarea>
								</td>
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