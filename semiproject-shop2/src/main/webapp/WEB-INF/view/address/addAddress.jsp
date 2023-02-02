<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>addAddress.jsp</title>
		
	</head>
	
	
	<body>
		<div>
			<div>
				<h1>배송지 추가</h1>
			</div>

			<div>
				<form action = "${pageContext.request.contextPath }/customer/addAddress" method = "post">
					<div>
						<table border = "1">
							<tr>
								<th>address</th>
								<td>
									<textarea rows="5" cols="50" name = "address"></textarea>
								</td>
							</tr>
						</table>
					</div>
					
					<div>
						<button type = "submit">추가</button>
					</div>
					
				</form>
			</div>		
		</div>
	</body>
</html>