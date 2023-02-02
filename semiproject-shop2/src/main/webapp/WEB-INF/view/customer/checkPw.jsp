
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>비밀번호 확인</title>
		<link href="https://fonts.googleapis.com/css?family=Montserrat:500,800" rel="stylesheet">
		<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/dist/style.css">
		
	    <script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
		
	    <script type="text/javascript">
	        $(document).ready(function() {
	        	
				// 비밀번호 확인
				$('#signinBtn').click(function() {
					
					// 디버깅
					// console.log($('#customerPw').val());
					
					// PW 유효성 체크
					if( ($('#customerPw').val().length) < 1) {
						alert('비밀번호가 입력되지 않았습니다.');
						$('#customerPw').focus();
						return;
					}	
					
					$('#signinForm').submit();
				});
	        });
	    </script>		
		
		
	</head>
	
	<body>
		<div class = "container">
			<div>
				<h1>비밀번호 확인</h1>
			</div>
			
			<div class = "wrapper">    
				<!-- Form -->
				<form id="signinForm" action="${pageContext.request.contextPath}/customer/checkPw" method="post">
					<div>
						<input type = "hidden" name = "targetUrl" id = "targetUrl" value = "${targetUrl }">
					</div>
				
					<!-- password input -->
					<div class="input__block">
						<input type="password" placeholder="Password" class="input" id="customerPw" name="customerPw" value="1234">
					</div>
					
					<!-- Login button -->
					<button class="signin__btn" id="signinBtn" type="button">확인</button>
				</form>			
			</div>
		</div>
	</body>
</html>