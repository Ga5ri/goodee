<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>modifyEmpByAdmin.jsp</title>
		
		<!-- jQuery -->
		<!-- CDN 주소 추가 방식 -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
		
	</head>
	
	
	<body>
		<div>
			<div>
				<h1>emp By Admin 수정</h1>
			</div>
			
			<div>
				<form action = "${pageContext.request.contextPath }/emp/modifyEmpByAdmin" method = "post">
					<div>
						<table border = "1">
							<tr>
								<th>empCode</th>
								<td>
									<input type = "text" name = "empCode" id = "empCode" value = "${emp.empCode }" readonly = "readonly">
								</td>
							</tr>
							
							<tr>
								<th>empId</th>
								<td>
									<input type = "text" name = "empId" id = "empId" value = "${emp.empId }" readonly = "readonly">
								</td>
							</tr>
							
							<tr>
								<th>empName</th>
								<td>
									<input type = "text" name = "empName" value = "${emp.empName }" id = "empName">
								</td>
							</tr>
							
							<tr>
								<th>active</th>
								<td>
									<c:if test="${emp.active == 'Y' }">
										<input type = "radio" name = "active" value = "Y" checked>Y
										<input type = "radio" name = "active" value = "N">N
									</c:if>
									
									<c:if test="${emp.active == 'N' }">
										<input type = "radio" name = "active" value = "Y">Y
										<input type = "radio" name = "active" value = "N" checked>N
									</c:if>
								</td>
							</tr>
							
							<tr>
								<th>authCode</th>
								<td>
									<select name = "authCode">
										<c:if test="${emp.authCode == 0}">
											<option value = "0" selected>0 : 관리자</option>
											<option value = "1">1 : 사업자</option>
										</c:if>
										<c:if test="${emp.authCode == 1}">
											<option value = "0">0 : 관리자</option>
											<option value = "1" selected>1 : 사업자</option>
										</c:if>
									</select>
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
						<button type = "submit">수정</button>
					</div>
				</form>
			</div>
		</div>		
	</body>
</html>