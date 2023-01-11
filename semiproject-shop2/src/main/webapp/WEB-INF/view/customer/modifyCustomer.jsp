<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>modifyCustomer.jsp</title>
		
		<!-- jQuery -->
		<!-- CDN 주소 추가 방식 -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		
	</head>
	
	
	<body>
		<div>
			<div>
				<h1>CUSTOMER 수정</h1>
			</div>
			
			<div>
				<form action = "${pageContext.request.contextPath }/customer/modifyCustomer" method = "post">
					<div>
						<table border = "1">
							<tr>
								<th>customerId</th>
								<td>
									<input type = "text" name = "customerId" id = "customerId" value = "${customer.customerId }" readonly = "readonly">
								</td>
							</tr>
							
							<tr>
								<th>customerName</th>
								<td>
									<input type = "text" name = "customerName" value = "${customer.customerName }" id = "customerName">
								</td>
							</tr>
							
							<tr>
								<th>customerPhone</th>
								<td>
									<input type = "text" name = "customerPhone" value = "${customer.customerPhone }" id = "customerPhone">
								</td>
							</tr>
							
							<tr>
								<th>point</th>
								<td>
									<input type = "text" name = "point" value = "${customer.point }" id = "point" readonly = "readonly">
								</td>
							</tr>
							
							<tr>
								<th>createdate</th>
								<td>
									<input type = "text" name = "createdate" id = "createdate" value = "${customer.createdate }" readonly = "readonly">
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