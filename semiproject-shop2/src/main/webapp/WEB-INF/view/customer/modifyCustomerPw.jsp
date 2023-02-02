<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>modifyCustomerPw.jsp</title>
		
	</head>
	
	
	<body>
		<div>
			<div>
				<h1>customer 비밀번호 변경</h1>
			</div>
		
			<div>
				<form action = "${pageContext.request.contextPath }/customer/modifyCustomerPw" method = "post">
					<div>
						<table border = "1">
							<tr>
								<th>새로운 비밀번호</th>
								<td>
									<input type = "password" name = "customerNewPw">
								</td>
							</tr>
							
							<tr>
								<th>새로운 비밀번호 확인</th>
								<td>
									<input type = "password" name = "customerNewPwCheck">
								</td>
							</tr>
						</table>
					</div>
				
					<div>
						<button type = "submit">변경</button>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>