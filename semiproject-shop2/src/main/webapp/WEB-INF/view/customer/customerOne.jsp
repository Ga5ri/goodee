<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>customerOne.jsp</title>
		
		<!-- jQuery -->
		<!-- CDN 주소 추가 방식 -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		
	</head>
	
	
	<body>
		<div>
			<div>
				<h1>CUSTOMER ONE</h1>
			</div>
			
			<div>
				<table border = "1">
					<tr>
						<th>CustomerCode</th>
						<td>
							<input type = "text" name = "customerCode" id = "customerCode" value = "${customer.customerCode }" readonly = "readonly">
						</td>
					</tr>
					
					<tr>
						<th>customerId</th>
						<td>
							<input type = "text" name = "customerId" id = "customerId" value = "${customer.customerId }" readonly = "readonly">
						</td>
					</tr>
					
					<tr>
						<th>customerName</th>
						<td>
							<input type = "text" name = "customerName" value = "${customer.customerName }" readonly = "readonly">
						</td>
					</tr>
					
					<tr>
						<th>customerPhone</th>
						<td>
							<input type = "text" name = "customerPhone" value = "${customer.customerPhone }" readonly = "readonly">
						</td>
					</tr>
					
					<tr>
						<th>point</th>
						<td>
							<input type = "text" name = "point" value = "${customer.point }" readonly = "readonly">
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
				<button type = "button" 
					onClick="location.href='${pageContext.request.contextPath }/customer/modifyCustomerByAdmin?customerId=${customer.customerId }'">
						수정
				</button>
				<button type = "button" 
					onClick="location.href='${pageContext.request.contextPath }/customer/deleteCustomer?customerId=${customer.customerId }'">
						삭제
				</button>
			</div>
		</div>		
	</body>
</html>