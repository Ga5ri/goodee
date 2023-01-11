<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>login</title>
		
	</head>
	<body>
		<h2>Login</h2>
		<div>
		<!-- 로그인 label -->
		<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Customer</label>
    	<input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Emp</label>
		  
		<!--Customer 로그인-->
		<form action="${pageContext.request.contextPath}/login" method="post">
			<div>
				<label>Customer ID</label>
				<input type="text"  name="customerId">
			</div>
			<div>
				<label>Password</label>
				<input type="password" name="customerPw">
			</div>
			<div>
		    	<button type="submit">로그인</button>
		    </div>
	    </form>
			<div>
			 <a href="${pageContext.request.contextPath}/customer/addCustomer">회원가입</a>  
		    </div>
		
		<!--Emp 로그인-->
		<form action="${pageContext.request.contextPath}/login" method="post">
			<div>
				<label>Emp ID</label>
				<input type="text"  name="empId">
			</div>
			<div>
				<label>Password</label>
				<input type="password" name="empPw">
			</div>
			<div>
		    	<button type="submit">로그인</button>
		    </div>
	    </form>
			<div>
			 <a href="${pageContext.request.contextPath}/emp/addEmp">사원가입</a>  
		    </div>
		</div>
	</body>
</html>