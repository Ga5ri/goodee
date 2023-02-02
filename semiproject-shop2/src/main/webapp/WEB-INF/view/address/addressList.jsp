<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>customerAddressList.jsp</title>
		
	</head>
	
	
	<body>
		<div>
			<div>
				<h1>배송지 관리</h1>
			</div>

			<div>
				<table border = "1">
					<tr>
						<th>address</th>
						<th>수정</th>
						<th>삭제</th>
					</tr>
	
					<c:forEach var="address" items="${addressList }">
						<tr>
							<td>${address.address }</td>
		
							<td>
								<a href = "${pageContext.request.contextPath }/customer/modifyAddress?addressCode=${address.addressCode }">수정</a>
							</td>
							
							<td>
								<a href = "${pageContext.request.contextPath }/customer/deleteAddress?addressCode=${address.addressCode }">삭제</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>


			<div>
				<a href = "${pageContext.request.contextPath }/customer/addAddress">배송지 추가</a>
			</div>
		
		
		
		</div>
		
	</body>
</html>