<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>modifyAddress.jsp</title>
		
	</head>
	
	
	<body>
		<div>
			<div>
				<h1>배송지 수정</h1>
			</div>

			<div>
				<form action = "${pageContext.request.contextPath }/customer/modifyAddress" method = "post">
					<div>
						<input type = "hidden" name = "addressCode" value = "${address.addressCode }">
					</div>
				
					<div>
						<table border = "1">
							<tr>
								<th>address</th>
								<td>
									<textarea rows="5" cols="50" name = "address">${address.address }</textarea>
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