<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>비밀번호 확인</title>
	</head>
	
	<body>
		<div>
			<div>
				<h1>비밀번호 확인</h1>
			</div>
			
			<div>
				<form action = "${pageContext.request.contextPath }/customer/checkPw" method = "post" >
					<div>
						<input type = "hidden" name = "targetUrl" id = "targetUrl" value = "${targetUrl }">
					</div>
				
					<div>
						<table border = "1">
							<tr>
								<th>Password</th>
								<td>
									<input type = "password" name = "customerPw" id = "customerPw">
								</td>
							</tr>
						</table>
					</div>
					
					<div>
						<button type = "submit">확인</button>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>